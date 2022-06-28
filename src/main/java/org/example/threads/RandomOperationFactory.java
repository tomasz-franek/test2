package org.example.threads;

import org.example.tokenizer.OperationEnum;

import java.security.SecureRandom;

public class RandomOperationFactory implements OperationFactoryInterface {
	private static final int SIZE = OperationEnum.values().length;
	private static final SecureRandom random = new SecureRandom();

	public OperationEnum generateOperation(){
		return OperationEnum.values()[random.nextInt(SIZE)];
	}
}
