package org.example.calculation;

import org.example.tokenizer.Token;
import org.example.tokenizer.TokenType;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class CalculationSubtractTest {

	@Test
	public void calculate_correctValues_expectedResult() {

		CalculationSubtract calculation = new CalculationSubtract();
		calculation.setArgs(
				new Token(TokenType.VALUE,"31"),
				new Token(TokenType.VALUE,"56"));
		calculation.calculate();
		assertThat(calculation.getResult().toString(), CoreMatchers.is("-25.0"));
	}
}
