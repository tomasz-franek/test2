package org.example.tokenizer;

import junit.framework.TestCase;
import org.example.calculation.CalculationAdd;
import org.example.calculation.CalculationDivide;
import org.example.calculation.CalculationMultiply;
import org.example.calculation.CalculationSubtract;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class OperationEnumTest{
	@Test
	public void fromTokenValue_add_correctOperationEnum() {
		OperationEnum operationEnum = OperationEnum.fromTokenValue("+");
		assertThat(operationEnum.getValue(),is(OperationEnum.ADD.getValue()));
	}

	@Test
	public void fromTokenValue_subtract_correctOperationEnum() {
		OperationEnum operationEnum = OperationEnum.fromTokenValue("-");
		assertThat(operationEnum.getValue(),is(OperationEnum.SUBTRACT.getValue()));
	}

	@Test
	public void fromTokenValue_divide_correctOperationEnum() {
		OperationEnum operationEnum = OperationEnum.fromTokenValue("/");
		assertThat(operationEnum.getValue(),is(OperationEnum.DIVIDE.getValue()));
	}

	@Test
	public void fromTokenValue_multiply_correctOperationEnum() {
		OperationEnum operationEnum = OperationEnum.fromTokenValue("*");
		assertThat(operationEnum.getValue(),is(OperationEnum.MULTIPLY.getValue()));
	}

	@Test
	public void fromTokenValue_wrongValue_null() {
		OperationEnum operationEnum = OperationEnum.fromTokenValue("?");
		assertThat(operationEnum,is(nullValue()));
	}

	@Test
	public void getCalculationInterface_add_returnCorrectInterface(){
		assertThat(OperationEnum.ADD.getCalculationInterface(),instanceOf(CalculationAdd.class));
	}

	@Test
	public void getCalculationInterface_subtract_returnCorrectInterface(){
		assertThat(OperationEnum.SUBTRACT.getCalculationInterface(),instanceOf(CalculationSubtract.class));
	}

	@Test
	public void getCalculationInterface_multiply_returnCorrectInterface(){
		assertThat(OperationEnum.MULTIPLY.getCalculationInterface(),instanceOf(CalculationMultiply.class));
	}

	@Test
	public void getCalculationInterface_divide_returnCorrectInterface(){
		assertThat(OperationEnum.DIVIDE.getCalculationInterface(),instanceOf(CalculationDivide.class));
	}
}
