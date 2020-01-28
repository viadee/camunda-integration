package de.viadee.camunda.connected.calculator.model;

import java.io.Serializable;

public class CalculateModel implements Serializable {
    private int num1;
    private int num2;
    private String operator;

    public int getNum1() {
        return num1;
    }

    public void setNum1(final int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(final int num2) {
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
