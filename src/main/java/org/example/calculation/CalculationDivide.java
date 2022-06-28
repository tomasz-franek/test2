package org.example.calculation;

import java.math.RoundingMode;

public class CalculationDivide extends BaseCalculation {

	@Override
	public void calculate() {
		result = firstNumber.divide(secondNumber, 8, RoundingMode.DOWN);
	}
}
