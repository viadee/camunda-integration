package de.viadee.camunda.connected.calculator.externaltaskworker;

import java.util.Collections;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
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
	
	@Value("${lockDuration}")
	private int lockduration;
	
	@Value("${topicSubscription}")
	private String topicSubscription;

	@Override
	public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

		long num1x = (long) externalTask.getVariable("num1");
		long num2x = (long) externalTask.getVariable("num2");
		String operator = (String) externalTask.getVariable("operator");
		int num1 = (int) num1x;
		int num2 = (int) num2x;
		
		Number ergebnis = calculatorService.calculate(num1, num2, operator);
		
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
