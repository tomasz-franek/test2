package org.example.tokenizer;

import org.example.calculation.CalculationInterface;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExpressionCalculation {

	public BigDecimal calculateExpression(List<Token> tokenList) throws IllegalArgumentException {
		List<Token> calculationTokenList = new ArrayList<>(tokenList);
		for (OperationEnum operationEnum : OperationEnum.operationsOrder) {
			calculationTokenList = calculateExpressionWithCurrentOperation(operationEnum, calculationTokenList);
		}
		return getToken(calculationTokenList, 0).getNumericValue();
	}

	private List<Token> calculateExpressionWithCurrentOperation(OperationEnum operationEnum, final List<Token> calculationTokenList) {
		List<Token> currentTokens = new ArrayList<>(calculationTokenList);
		while (currentTokens.stream().anyMatch(token -> token.getType() == TokenType.OPERATION
				&& operationEnum.getValue().equals(token.getValue()))) {
			calculateOperation(operationEnum, currentTokens);
		}
		return currentTokens;
	}

	private void calculateOperation(OperationEnum operationEnum, List<Token> currentTokens) {
		for (int i = 0; i < currentTokens.size(); i++) {
			Token operationToken = getToken(currentTokens, i);
			if (TokenType.OPERATION.equals(operationToken.getType())  && operationEnum.getValue().equals(operationToken.getValue())) {
					calculateValue(operationToken.getCalculationInterface(), currentTokens, i);
				currentTokens.remove(i+1);
				currentTokens.remove(i);
					return;
			}
		}
	}

	public Token getToken(List<Token> currentTokens, int index) {
		if(index >= 0 && index < currentTokens.size()) {
			return currentTokens.get(index);
		} else
			throw new IllegalArgumentException(String.format("Wrong token index %d %s", index,currentTokens.toString()));
	}

	private void calculateValue(CalculationInterface calculationInterface, List<Token> currentTokens, int i) {
		Token fistArg = getToken(currentTokens, i - 1);
		Token secondArg = getToken(currentTokens, i + 1);
		calculationInterface.setArgs(fistArg, secondArg);
		calculationInterface.calculate();
		fistArg.setNumericValue( calculationInterface.getResult());
	}
}
