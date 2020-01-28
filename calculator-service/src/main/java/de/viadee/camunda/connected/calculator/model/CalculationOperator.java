package de.viadee.camunda.connected.calculator.model;

public enum CalculationOperator {

    Plus("+"),
    Minus("-"),
    Geteilt("/"),
    Mal("*");

    private String symbolicValue;

    CalculationOperator(String s) {
        this.symbolicValue=s;
    }

    public static CalculationOperator getFromSymbolicValue(String symbolicValue){
        for (CalculationOperator operator: values()) {
            if (operator.symbolicValue.equals(symbolicValue)) {
                return operator;
            }
        }
        return CalculationOperator.Plus;
    }
}
