package org.example.threads;

import org.example.domain.TaskObject;

import java.security.SecureRandom;

public class TaskFactory implements TaskFactoryInterface {
	private final static int MAX_OPERATIONS = 20;
	private final NumberFactoryInterface numberFactory = new RandomNumberFactory();
	private final OperationFactoryInterface operationFactory = new RandomOperationFactory();
	private final SecureRandom random = new SecureRandom();

	public TaskObject generateTask(){
		TaskObject taskObject =new TaskObject();

		StringBuilder builder = new StringBuilder();
		int operations = random.nextInt(MAX_OPERATIONS) + 1;
		builder.append(numberFactory.generateNumber());
		for (int i = 0; i < operations; i++) {
			builder.append(operationFactory.generateOperation().getValue());
			builder.append(numberFactory.generateNumber());
		}
		taskObject.setTaskValue(builder.toString());
		return taskObject;
	}

}
