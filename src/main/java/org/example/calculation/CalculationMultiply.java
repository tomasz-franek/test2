package org.example.calculation;

public class CalculationMultiply extends BaseCalculation {

	@Override
	public void calculate() {
		result = firstNumber.multiply(secondNumber);
	}
}
