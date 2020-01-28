package de.viadee.camunda.connected.businessprocess.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Plain Old Java Delegate for logging the calculation activity and result.
 */
@Component("logger")
public class LoggerDelegate implements JavaDelegate {

	private final Logger LOG = LoggerFactory.getLogger(LoggerDelegate.class);

	public void execute(DelegateExecution execution) throws Exception {

		LOG.info("\n\n  Ergebnis: {} {} {} = {} ("
				+ "processDefinitionId={}, activtyId={}, activtyName='{}', processInstanceId={}, businessKey={}, executionId={}\n\n",
				execution.getVariable("num1"), execution.getVariable("operator"), execution.getVariable("num2"),
				execution.getVariable("result"), execution.getProcessDefinitionId(), execution.getCurrentActivityId(),
				execution.getCurrentActivityName(), execution.getProcessInstanceId(), execution.getProcessBusinessKey(),
				execution.getId());

	}

}
