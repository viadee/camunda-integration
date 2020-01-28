package de.viadee.camunda.connected.businessprocess.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.LongValue;
import org.camunda.bpm.engine.variable.value.StringValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import de.viadee.camunda.connected.businessprocess.model.CalculateModel;

/**
 * Delegate for pushing a remote RPA based calculation service in a synchronous manner.
 * The REST call to the service is implemented based on Spring infrastructure.
 */
@Component
public class CalculatorRpaDelegate implements JavaDelegate {

    @Autowired
    private RestTemplate template;

    @Value("${calculation.server.url}")
    private String baseUrl;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        // Input
        LongValue number1 = execution.getVariableTyped("num1");
        StringValue operator = execution.getVariableTyped("operator");
        LongValue number2 = execution.getVariableTyped("num2");

        // Process
        CalculateModel request = new CalculateModel(number1.getValue(),number2.getValue(),operator.getValue());
        Long result = template.postForObject(baseUrl+"/syncRpa/calc", request,Long.class);

        // Output
        execution.setVariable("result", result);

    }
}
