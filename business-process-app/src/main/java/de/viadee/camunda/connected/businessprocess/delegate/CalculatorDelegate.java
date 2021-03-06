package de.viadee.camunda.connected.businessprocess.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.LongValue;
import org.camunda.bpm.engine.variable.value.StringValue;
import org.springframework.stereotype.Component;

import de.viadee.camunda.connected.businessprocess.calculator.MiniCalculator;
import de.viadee.camunda.connected.businessprocess.calculator.Operator;

/**
 * Plain Old Java Delegate for a calculation facilitating an embedded calculator
 * implementation.
 */
@Component("calculatorPlainDelegate")
public class CalculatorDelegate implements JavaDelegate {

	private MiniCalculator calculator = new MiniCalculator();

	public void execute(DelegateExecution execution) throws Exception {

		// Input
		LongValue number1 = execution.getVariableTyped("num1");
		LongValue number2 = execution.getVariableTyped("num2");
		Operator operator = Operator.valueOf(execution.getVariableTyped("operator").getValue().toString());
		
		// Process
		Number result = calculator.processCalculation(number1.getValue(), operator, number2.getValue());

		// Output
		execution.setVariable("result", result);
	}

}
