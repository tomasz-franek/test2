package org.example.threads;

import java.security.SecureRandom;

public class RandomNumberFactory implements NumberFactoryInterface {
	private static final int SIZE = 100_000;
	private static final SecureRandom random = new SecureRandom();

	public int generateNumber(){
		return random.nextInt(SIZE);
	}
}
