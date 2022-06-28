package org.example.threads;

import org.example.domain.TaskObject;
import org.example.tokenizer.ExpressionCalculation;
import org.example.tokenizer.Token;
import org.example.tokenizer.Tokenizer;
import org.example.tokenizer.TaskObjectValidatorInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskConsumer extends TaskRunnableThread {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskConsumer.class);
	Tokenizer tokenizer;
	TaskObjectValidatorInterface tokenizerValidator;
	ExpressionCalculation expressionCalculation;

	public TaskConsumer(LinkedBlockingQueue<TaskObject> queue,
						TaskObjectValidatorInterface taskObjectValidatorInterface,
						ExpressionCalculation expressionCalculation) {
		this.queue = queue;
		this.tokenizer = new Tokenizer();
		this.tokenizerValidator = taskObjectValidatorInterface;
		this.expressionCalculation = expressionCalculation;
	}

	@Override
	public void run() {
		LOGGER.info(String.format("Start consumer id=%s",Thread.currentThread().getId()));
		while (this.running || !queue.isEmpty()){
			if( !queue.isEmpty()) {
				TaskObject taskObject = queue.poll();
				if(taskObject != null) {
					queue.remove(taskObject);
					processTaskObject(taskObject);
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				LOGGER.error("interrupted sleep", e);
			}
		}
		LOGGER.info(String.format("Stop consumer id=%s",Thread.currentThread().getId()));
	}

	private void processTaskObject(TaskObject taskObject) {
		if(tokenizerValidator.validate(taskObject)){
			List<Token> tokenList = tokenizer.tokenizeTaskObject(taskObject);
			try {
				BigDecimal result = expressionCalculation.calculateExpression(tokenList);
				printResult(taskObject,result);
			} catch (Exception e) {
				LOGGER.error("Task: " + taskObject.getTaskValue(),e);
			}
		}
	}

	private void printResult(TaskObject taskObject, BigDecimal result) {
		LOGGER.info(String.format("Result of task %s = %s",taskObject.getTaskValue(),result.toString()));
	}
}
