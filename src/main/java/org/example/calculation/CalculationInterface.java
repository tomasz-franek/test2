package org.example.calculation;

import org.example.tokenizer.Token;

import java.math.BigDecimal;

public interface CalculationInterface {
	void calculate();
	BigDecimal getResult();
	void setArgs(Token firstArg, Token secondArg);
}
