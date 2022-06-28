package org.example.tokenizer;

import org.example.calculation.CalculationAdd;
import org.example.calculation.CalculationDivide;
import org.example.calculation.CalculationInterface;
import org.example.calculation.CalculationMultiply;
import org.example.calculation.CalculationSubtract;

import java.util.Arrays;
import java.util.List;

public enum OperationEnum {
	ADD("+", new CalculationAdd()),
	SUBTRACT("-",  new CalculationSubtract()),
	DIVIDE("/",  new CalculationDivide()),
	MULTIPLY("*",  new CalculationMultiply());

	private final String value;
	private final CalculationInterface calculationInterface;

	OperationEnum(String value, CalculationInterface calculationInterface) {
		this.value = value;
		this.calculationInterface = calculationInterface;
	}

	public String getValue() {
		return value;
	}

	public CalculationInterface getCalculationInterface() {
		return this.calculationInterface;
	}

	public static OperationEnum fromTokenValue(String value){
		for (OperationEnum operationEnum : OperationEnum.values()) {
			if(operationEnum.getValue().equals(value)){
				return operationEnum;
			}
		}
		return null;
	}

	public static final List<OperationEnum> operationsOrder = Arrays.asList(
			DIVIDE,
			MULTIPLY,
			SUBTRACT,
			ADD);
}
