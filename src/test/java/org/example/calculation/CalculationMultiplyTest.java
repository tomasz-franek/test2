package org.example.calculation;

import org.example.tokenizer.Token;
import org.example.tokenizer.TokenType;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class CalculationMultiplyTest {

	@Test
	public void calculate_correctValues_expectedResult() {

		CalculationMultiply calculation = new CalculationMultiply();
		calculation.setArgs(
				new Token(TokenType.VALUE,"23"),
				new Token(TokenType.VALUE,"56"));
		calculation.calculate();
		assertThat(calculation.getResult().toString(), CoreMatchers.is("1288.00"));
	}
}
