package de.viadee.camunda.connected.businessprocess.calculator;

/**
 * Calculation operators
 */
public enum Operator {

	Plus("+"),
	Minus("-"),
	Geteilt("/"),
	Mal("*");

	private String symbolicValue;

	Operator(String s) {
		this.symbolicValue=s;
	}

	public static Operator getFromSymbolicValue(String symbolicValue){
		for (Operator operator: values()) {
			if (operator.symbolicValue.equals(symbolicValue)) {
				return operator;
			}
		}
		return Operator.Plus;
	}
}
