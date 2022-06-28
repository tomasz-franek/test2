package org.example.calculation;

public class CalculationAdd extends BaseCalculation implements CalculationInterface {

	@Override
	public void calculate() {
		result = firstNumber.add(secondNumber);
	}
}
