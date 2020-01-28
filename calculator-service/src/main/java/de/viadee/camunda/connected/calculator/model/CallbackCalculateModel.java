package de.viadee.camunda.connected.calculator.model;

public class CallbackCalculateModel extends CalculateModel {

    private String correlationId;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(final String correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public String toString() {
        return "CallbackCalculateModel{" +
                "correlationId'" + correlationId + '\'' +
                ", num1=" + getNum1() +
                ", num2=" + getNum2() +
                ", operator='" + getOperator() + '\'' +
                '}';
    }
}
