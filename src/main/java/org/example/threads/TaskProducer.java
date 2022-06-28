package org.example.threads;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.config.ApplicationProperties;
import org.example.domain.TaskObject;


import java.util.concurrent.LinkedBlockingQueue;

public class TaskProducer extends TaskRunnableThread {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskProducer.class);
	private final TaskFactoryInterface taskFactoryInterface;
	private boolean producing = true;

	public TaskProducer(LinkedBlockingQueue<TaskObject> queue, TaskFactoryInterface taskFactoryInterface) {
		this.queue = queue;
		this.taskFactoryInterface = taskFactoryInterface;
	}

	@Override
	public void run() {
		int size;
		LOGGER.info(String.format("Start producer id=%s",Thread.currentThread().getId()));
		while (this.running){
			size = queue.size();
			if(producing) {
				produceTask(size);
			} else {
				waiting(size);
			}
		}
		LOGGER.info(String.format("Stop producer id=%s",Thread.currentThread().getId()));
	}


	private void waiting(int size) {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			LOGGER.error("unable sleep", e);
		}
		if(size <= (ApplicationProperties.QUEUE_SIZE/2)){
			producing = true;
			LOGGER.info(String.format("Producing producer id=%s",Thread.currentThread().getId()));
		}
	}

	private void produceTask(int size) {
		try {
			queue.put(taskFactoryInterface.generateTask());
			if (size + 1 >= ApplicationProperties.QUEUE_SIZE) {
				LOGGER.info(String.format("Waiting producer id=%s",Thread.currentThread().getId()));
				this.producing = false;
			}
		} catch (InterruptedException e) {
			LOGGER.error("Unable to put com.company.domain.TaskObject to queue", e);
		}
	}
}
