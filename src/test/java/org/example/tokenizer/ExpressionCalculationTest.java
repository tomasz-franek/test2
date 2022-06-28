package org.example.tokenizer;

import org.example.domain.TaskObject;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

public class ExpressionCalculationTest {
	ExpressionCalculation expressionCalculation;
	Tokenizer tokenizer;
	TaskObject taskObject = new TaskObject();

	@Before
	public void init(){
		expressionCalculation = new ExpressionCalculation();
		tokenizer = new Tokenizer();
	}

	@Test
	public void calculateExpression_subtractOperation_correctResult() {
		taskObject.setTaskValue("100-110");
		List<Token> tokenList = tokenizer.tokenizeTaskObject(taskObject);
		BigDecimal result = expressionCalculation.calculateExpression(tokenList);
		assertThat(result.toString(),is("-10.0"));
	}

	@Test
	public void calculateExpression_divideOperation_correctResult() {
		taskObject.setTaskValue("100/20");
		List<Token> tokenList = tokenizer.tokenizeTaskObject(taskObject);
		BigDecimal result = expressionCalculation.calculateExpression(tokenList);
		assertThat(result.toString(),is("5.00000000"));
	}

	@Test
	public void calculateExpression_addOperation_correctResult() {
		taskObject.setTaskValue("100+20");
		List<Token> tokenList = tokenizer.tokenizeTaskObject(taskObject);
		BigDecimal result = expressionCalculation.calculateExpression(tokenList);
		assertThat(result.toString(),is("120.0"));
	}

	@Test
	public void calculateExpression_multiplyOperation_correctResult() {
		taskObject.setTaskValue("14*7");
		List<Token> tokenList = tokenizer.tokenizeTaskObject(taskObject);
		BigDecimal result = expressionCalculation.calculateExpression(tokenList);
		assertThat(result.toString(),is("98.00"));
	}

	@Test
	public void calculateExpression_allOperations_correctResult() {
		taskObject.setTaskValue("200*3/10-100");
		List<Token> tokenList = tokenizer.tokenizeTaskObject(taskObject);
		BigDecimal result = expressionCalculation.calculateExpression(tokenList);
		assertThat(result.toString(),is("-40.000000000"));
	}

	@Test
	public void calculateExpression_fewSubtractOperations_correctResult() {
		taskObject.setTaskValue("5287-6569-3735-103/4971");
		List<Token> tokenList = tokenizer.tokenizeTaskObject(taskObject);
		BigDecimal result = expressionCalculation.calculateExpression(tokenList);
		assertThat(result.toString(),is("-5017.02072017"));
	}

	@Test
	public void calculateExpression_divideAndMultiplyOperations_correctResult() {
		taskObject.setTaskValue("9250+7525*4040/9250");
		List<Token> tokenList = tokenizer.tokenizeTaskObject(taskObject);
		BigDecimal result = expressionCalculation.calculateExpression(tokenList);
		assertThat(result.toString(),is("12536.594543750"));
	}

	@Test
	public void getToken_wrongIndex_generatedException(){
		taskObject.setTaskValue("1+1");
		List<Token> tokenList = tokenizer.tokenizeTaskObject(taskObject);
		Exception exception = assertThrows(IllegalArgumentException.class, () ->
				expressionCalculation.getToken(tokenList,-1));
		assertThat(exception.getMessage(),is(
				"Wrong token index -1 [" +
						"Token{type=VALUE, value='1', numericValue=1.0, operation=null}, " +
						"Token{type=OPERATION, value='+', numericValue=null, operation=ADD}, " +
						"Token{type=VALUE, value='1', numericValue=1.0, operation=null}]"));

	}
}
