package org.example.calculation;

import org.example.tokenizer.OperationEnum;
import org.example.tokenizer.Token;
import org.example.tokenizer.TokenType;

public class CalculationOperationFactory {
	public static CalculationInterface getCalculation(Token token) {
		if (!TokenType.OPERATION.equals(token.getType())) {
			throw new IllegalArgumentException(String.format("Current token is not operation %s", token));
		}
		OperationEnum operationEnum = OperationEnum.fromTokenValue(token.getValue());
		if(operationEnum != null) {
			return operationEnum.getCalculationInterface();
		}else {
			return null;
		}
	}
}
