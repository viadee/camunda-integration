package de.viadee.camunda.connected.businessprocess.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.LongValue;
import org.camunda.bpm.engine.variable.value.StringValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;

import de.viadee.camunda.connected.businessprocess.model.CallbackCalculateModel;

import java.util.UUID;

/**
 * Delegate for pushing a remote calculation service in an asynchronous manner.
 * The REST call to the service is implemented based on Spring infrastructure.
 */
@Component("sendDelegate")
public class SendDelegate implements JavaDelegate {

    @Autowired
	private RestTemplate template;

	@Value("${calculation.server.url}")
	private String baseUrl;
	
	public void execute(DelegateExecution execution) throws Exception {
    	
		// Input
		LongValue number1 = execution.getVariableTyped("num1");
		StringValue operator = execution.getVariableTyped("operator");
    	LongValue number2 =  execution.getVariableTyped("num2");

    	// Process
		String correlationId = UUID.randomUUID().toString();
		CallbackCalculateModel request = new CallbackCalculateModel(correlationId,number1.getValue(),number2.getValue(),operator.getValue());

		template.postForObject(baseUrl+"/async/calc", request,String.class);

		// Output
    	execution.setVariable("correlationId", correlationId);
    	
    }



}
