package org.example.calculation;

import org.example.tokenizer.Token;
import org.example.tokenizer.TokenType;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class CalculationDivideTest {

	@Test
	public void calculate_correctValues_expectedResult() {

		CalculationDivide calculation = new CalculationDivide();
		calculation.setArgs(
				new Token(TokenType.VALUE,"3"),
				new Token(TokenType.VALUE,"5"));
		calculation.calculate();
		assertThat(calculation.getResult().toString(), CoreMatchers.is("0.60000000"));
	}
}
