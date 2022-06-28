import org.apache.log4j.BasicConfigurator;
import org.example.config.ApplicationProperties;
import org.example.domain.TaskObject;
import org.example.threads.TaskConsumer;
import org.example.threads.TaskFactory;
import org.example.threads.TaskProducer;
import org.example.threads.TaskRunnableThread;
import org.example.tokenizer.ExpressionCalculation;
import org.example.tokenizer.TaskObjectValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();
		List<TaskRunnableThread> threads = new ArrayList<>();
		LinkedBlockingQueue<TaskObject> taskObjectBlockingQueue = new LinkedBlockingQueue<>(ApplicationProperties.QUEUE_SIZE);
		TaskFactory taskFactory = new TaskFactory();

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			LOGGER.info("Shutting down application ...");
			stopThreads(threads);
			LOGGER.info("All thread stopped");
			LOGGER.info("Queue length " + taskObjectBlockingQueue.size());
		}));

		LOGGER.info("Start application ...");
		for (int i = 0; i < ApplicationProperties.PRODUCERS; i++) {
			threads.add(new TaskProducer(taskObjectBlockingQueue,taskFactory));
		}

		for (int i = 0; i < ApplicationProperties.CONSUMERS; i++) {
			threads.add(new TaskConsumer(taskObjectBlockingQueue, new TaskObjectValidator(), new ExpressionCalculation()));
		}

		startThreads(threads);
	}

	private static void startThreads(List<TaskRunnableThread> threads) {
		threads.forEach(Thread::start);
	}

	private static void stopThreads(List<TaskRunnableThread> runnableLst) {
		runnableLst.forEach(TaskRunnableThread::stopRunnable);
		runnableLst.forEach(taskRunnable -> {
			try {
				taskRunnable.join();
			} catch (InterruptedException e) {
				LOGGER.error("interrupted join",e);
			}
		});
	}
}
