package de.viadee.camunda.connected.businessprocess.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MiniCalculator {

    private final Logger LOG = LoggerFactory.getLogger(MiniCalculator.class);

	public Number processCalculation(long number1, String operator, long number2) {
    	if (Operator.Plus.toString().equals(operator)) {
			return number1 + number2;
    	} else if (Operator.Minus.toString().equals(operator)) {
    		return number1 - number2;
    	} else if (Operator.Mal.toString().equals(operator)) {
    		return number1 * number2;
    	} else if (Operator.Geteilt.toString().equals(operator)) {
    		return new BigDecimal(number1).divide(new BigDecimal(number2), 2, RoundingMode.CEILING);
    	} else {
    		LOG.warn("Invalid operator: {}", operator);
    		return 0;
		}
	}
}