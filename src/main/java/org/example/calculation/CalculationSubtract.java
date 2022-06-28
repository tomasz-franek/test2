package org.example.calculation;

public class CalculationSubtract extends BaseCalculation {

	@Override
	public void calculate() {
		result = firstNumber.subtract(secondNumber);
	}
}
