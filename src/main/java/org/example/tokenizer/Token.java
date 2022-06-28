package org.example.tokenizer;

import org.example.calculation.CalculationInterface;
import org.example.calculation.CalculationOperationFactory;

import java.math.BigDecimal;
import java.util.Objects;

public class Token {
	private TokenType type = null;
	private String value = "";
	private BigDecimal numericValue = null;
	private CalculationInterface calculationInterface = null;
	private OperationEnum operation;

	public Token(TokenType tokenType, String tokenValue) {
		this.type = tokenType;
		this.value = tokenValue;
		if(TokenType.OPERATION.equals(type)){
			this.calculationInterface = CalculationOperationFactory.getCalculation(this);
			this.operation = OperationEnum.fromTokenValue(tokenValue);
		}else {
			this.numericValue = BigDecimal.valueOf(Double.parseDouble(tokenValue));
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Token token = (Token) o;
		return type == token.type && Objects.equals(value, token.getValue()) && Objects.equals(operation,token.getOperation());
	}

	@Override
	public String toString() {
		return "Token{" +
				"type=" + type +
				", value='" + value + '\'' +
				", numericValue=" + numericValue +
				", operation=" + operation +
				'}';
	}

	public TokenType getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public BigDecimal getNumericValue() {
		return numericValue;
	}

	public void setNumericValue(BigDecimal numericValue) {
		this.numericValue = numericValue;
		this.value = numericValue.toString();
	}

	public OperationEnum getOperation() {
		return operation;
	}

	public CalculationInterface getCalculationInterface() {
		return calculationInterface;
	}
}
