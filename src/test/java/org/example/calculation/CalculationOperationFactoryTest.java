package org.example.calculation;

import org.example.tokenizer.Token;
import org.example.tokenizer.TokenType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

public class CalculationOperationFactoryTest {

	@Test
	public void getCalculation_forTokenValue_generateException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () ->
				CalculationOperationFactory.getCalculation(new Token(TokenType.VALUE,"1")));
		assertThat(exception.getMessage(),is(
				"Current token is not operation Token{type=VALUE, value='1', numericValue=1.0, operation=null}"));
	}

	@Test
	public void getCalculation_wrongOperation_null() {
		CalculationInterface calculationInterface = CalculationOperationFactory.getCalculation(
				new Token(TokenType.OPERATION,"?"));
		assertThat(calculationInterface,is(nullValue()));
	}

	@Test
	public void getCalculation_addOperation_correctInterface() {
		CalculationInterface calculationInterface = CalculationOperationFactory.getCalculation(
				new Token(TokenType.OPERATION,"+"));
		assertThat(calculationInterface,instanceOf(CalculationAdd.class));
	}

	@Test
	public void getCalculation_subtractOperation_correctInterface() {
		CalculationInterface calculationInterface = CalculationOperationFactory.getCalculation(
				new Token(TokenType.OPERATION,"-"));
		assertThat(calculationInterface,instanceOf(CalculationSubtract.class));
	}
	@Test
	public void getCalculation_multiplyOperation_correctInterface() {
		CalculationInterface calculationInterface = CalculationOperationFactory.getCalculation(
				new Token(TokenType.OPERATION,"*"));
		assertThat(calculationInterface,instanceOf(CalculationMultiply.class));
	}

	@Test
	public void getCalculation_divideOperation_correctInterface() {
		CalculationInterface calculationInterface = CalculationOperationFactory.getCalculation(
				new Token(TokenType.OPERATION,"/"));
		assertThat(calculationInterface,instanceOf(CalculationDivide.class));
	}
}
