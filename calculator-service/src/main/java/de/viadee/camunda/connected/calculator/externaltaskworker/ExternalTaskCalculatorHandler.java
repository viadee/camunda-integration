package de.viadee.camunda.connected.calculator.externaltaskworker;

import java.util.Collections;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.LongValue;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.camunda.bpm.engine.variable.value.StringValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import de.viadee.camunda.connected.calculator.service.CalculatorService;

@Component
public class ExternalTaskCalculatorHandler implements ExternalTaskHandler {
	
	private final static Logger LOGGER = Logger.getLogger(ExternalTaskCalculatorHandler.class.getName());
	
	@Autowired
	private ExternalTaskClient externalTaskClient;
	
	@Autowired
	private CalculatorService calculatorService;
	
	@Value("${camunda.externalTask.lockDuration}")
	private int lockduration;
	
	@Value("${camunda.externalTask.calculatorWorker.topicSubscription}")
	private String topicSubscription;

	@Override
	public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

		StringValue operator = externalTask.getVariableTyped("operator");
		String operatorx = operator.getValue();
		
		LongValue num1 = externalTask.getVariableTyped("num1");
		Long num1x = num1.getValue();
		
		LongValue num2 = externalTask.getVariableTyped("num2");
		Long num2x = num2.getValue();
				
		Number ergebnis = calculatorService.calculate(num1x.intValue(), num2x.intValue(), operatorx);
		
		ObjectValue results = Variables
			      .objectValue(ergebnis) 
			      .create();
		
		 externalTaskService.complete(externalTask,
		          Collections.singletonMap("result", results));
		 
		 LOGGER.info(
				 "Der externe Task " + externalTask.getId() + " ist fertig!");
	}
	
	@PostConstruct
	private void register() {
			this.externalTaskClient.subscribe(this.topicSubscription).lockDuration(this.lockduration)
			.handler(this).open();
	}

}
