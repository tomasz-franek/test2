package org.example.calculation;

import org.example.tokenizer.Token;
import org.example.tokenizer.TokenType;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

public class BaseCalculationTest {
	BaseCalculation baseCalculation;
	@Before
	public void setUp() {
		baseCalculation = new CalculationAdd();
	}

	@Test
	public void setArgs_firstArgIsOperation_firstNumberIsZero() {
		Token firstToken = new Token(TokenType.OPERATION,"+");
		Token secondToken = new Token(TokenType.VALUE,"100");
		baseCalculation.setArgs(firstToken,secondToken);
		assertThat(baseCalculation.firstNumber, CoreMatchers.is(BigDecimal.ZERO));
	}

	@Test
	public void setArgs_secondArgIsOperation_firstNumberIsZero() {
		Token firstToken = new  Token(TokenType.VALUE,"100");
		Token secondToken = new Token(TokenType.OPERATION,"+");
		baseCalculation.setArgs(firstToken,secondToken);
		assertThat(baseCalculation.secondNumber, CoreMatchers.is(BigDecimal.ZERO));
	}

	@Test
	public void setArgs_bothTokensAreValues_correctValues() {
		Token firstToken = new  Token(TokenType.VALUE,BigDecimal.ONE.toString());
		Token secondToken = new Token(TokenType.VALUE,BigDecimal.TEN.toString());
		baseCalculation.setArgs(firstToken,secondToken);
		assertThat(baseCalculation.firstNumber.intValue(), CoreMatchers.is(BigDecimal.ONE.intValue()));
		assertThat(baseCalculation.secondNumber.intValue(), CoreMatchers.is(BigDecimal.TEN.intValue()));
	}
}
