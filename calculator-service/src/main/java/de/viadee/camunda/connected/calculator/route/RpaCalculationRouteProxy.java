package de.viadee.camunda.connected.calculator.route;

import org.apache.camel.Body;

import de.viadee.camunda.connected.calculator.model.CalculateModel;

public interface RpaCalculationRouteProxy {

    Number startRpaCalculation(@Body final CalculateModel model);

}
