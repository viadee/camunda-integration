package de.viadee.camunda.connected.businessprocess.model;

/**
 * Data model for the remote calculator response.
 */
public class CallbackCalculateModel extends CalculateModel {

	private static final long serialVersionUID = -1712545790324835051L;
	private String correlationId;

    public CallbackCalculateModel(Long number1, Long number2, String operator) {
        super(number1, number2, operator);
    }

    public CallbackCalculateModel(String uuid, long number1, long number2, String operator) {
        super(number1,number2,operator);
        this.setCorrelationId(uuid);
    }

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
