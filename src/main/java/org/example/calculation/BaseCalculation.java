package org.example.calculation;

import org.example.tokenizer.Token;
import org.example.tokenizer.TokenType;

import java.math.BigDecimal;

public abstract class BaseCalculation  implements CalculationInterface {
	protected BigDecimal firstNumber = BigDecimal.ZERO;
	protected BigDecimal secondNumber = BigDecimal.ZERO;
	protected BigDecimal result = BigDecimal.ZERO;

	public void setArgs(Token firstArg, Token secondArg){
		firstNumber = parseToken(firstArg);
		secondNumber = parseToken(secondArg);
	}

	private BigDecimal parseToken(Token token) {
		if(token != null && token.getOperation() == null) {
			assert (TokenType.VALUE.equals(token.getType()));
			return token.getNumericValue();
		}else{
			return BigDecimal.ZERO;
		}
	}

	@Override
	public BigDecimal getResult() {
		return result;
	}
}
