package de.viadee.camunda.connected.businessprocess.model;

import java.io.Serializable;

/**
 * Data Model for the remote calculator request.
 */
public class CalculateModel implements Serializable {

	private static final long serialVersionUID = -7675876666958922073L;
	private long num1;
    private long num2;
    private String operator;

    public CalculateModel(Long number1, Long number2, String operator) {
        this.setNum1(number1);
        this.setNum2(number2);
        this.setOperator(operator);
    }

    public long getNum1() {
        return num1;
    }

    public void setNum1(final long num1) {
        this.num1 = num1;
    }

    public long getNum2() {
        return num2;
    }

    public void setNum2(final long num2) {
        this.num2 = num2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(final String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "CalculateModel{" +
                "num1=" + num1 +
                ", num2=" + num2 +
                ", operator='" + operator + '\'' +
                '}';
    }
}
