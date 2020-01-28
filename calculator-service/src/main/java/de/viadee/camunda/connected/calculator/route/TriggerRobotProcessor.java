package de.viadee.camunda.connected.calculator.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class TriggerRobotProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        // somehow trigger RPA-Framework of your choice  (e.g. REST-Call)

    }
}
