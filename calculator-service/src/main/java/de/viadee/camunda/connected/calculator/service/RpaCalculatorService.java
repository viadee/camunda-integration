package de.viadee.camunda.connected.calculator.service;

import org.apache.camel.Produce;
import org.springframework.stereotype.Service;

import de.viadee.camunda.connected.calculator.model.CalculateModel;
import de.viadee.camunda.connected.calculator.route.RpaCalculationRouteProxy;

@Service
public class RpaCalculatorService {

    @Produce(uri="direct:startRpaCalculation")
    RpaCalculationRouteProxy rpaCalculationRouteProxy;

    public Number calculate(int num1, int num2, String op) {
        CalculateModel calculateModel = new CalculateModel();
        calculateModel.setNum1(num1);
        calculateModel.setNum2(num2);
        calculateModel.setOperator(op);
        return rpaCalculationRouteProxy.startRpaCalculation(calculateModel);
    }
}
