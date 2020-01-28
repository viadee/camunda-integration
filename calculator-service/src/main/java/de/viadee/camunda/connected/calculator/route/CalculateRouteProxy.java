package de.viadee.camunda.connected.calculator.route;

import org.apache.camel.Body;

import de.viadee.camunda.connected.calculator.model.CalculateModel;
import de.viadee.camunda.connected.calculator.model.CallbackCalculateModel;

import java.util.concurrent.Future;

public interface CalculateRouteProxy {

    Future<?> startCalc(@Body final CallbackCalculateModel model);
}
