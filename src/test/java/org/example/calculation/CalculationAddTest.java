package org.example.calculation;
import org.example.tokenizer.Token;
import org.example.tokenizer.TokenType;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;


public class CalculationAddTest {

	@Test
	public void calculate_correctValues_expectedResult() {

		CalculationAdd calculationAdd = new CalculationAdd();
		calculationAdd.setArgs(
				new Token(TokenType.VALUE,"3"),
				new Token(TokenType.VALUE,"5"));
		calculationAdd.calculate();
		assertThat(calculationAdd.getResult().intValue(), CoreMatchers.is(8));
	}
}
