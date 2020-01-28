package de.viadee.camunda.connected.calculator.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorService {

    public Number calculate(int num1, int num2, String op) {
        switch (op) {
            case "Plus":
                return num1 + num2;
            case "Minus":
                return num1 - num2;
            case "Geteilt":
                return new BigDecimal(num1).divide(new BigDecimal(num2));
            case "Mal":
                return num1 * num2;
        }
        throw new RuntimeException("Unknown operator. Calculation not possible!");
    }
}
