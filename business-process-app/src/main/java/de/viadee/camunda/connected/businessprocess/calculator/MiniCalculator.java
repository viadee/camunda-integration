package de.viadee.camunda.connected.businessprocess.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MiniCalculator {

    private final Logger LOG = LoggerFactory.getLogger(MiniCalculator.class);

    public Number processCalculation(long number1, Operator operator, long number2) {
        switch (operator) {
            case Plus:
                return number1 + number2;
                
            case Minus:
                return number1 - number2;

            case Mal:
                return number1 * number2;

            case Geteilt:
                return new BigDecimal(number1).divide(new BigDecimal(number2), 2, RoundingMode.HALF_UP);

            default:
                LOG.warn("Invalid operator: {}", operator);
                return 0;
        }
    }
}