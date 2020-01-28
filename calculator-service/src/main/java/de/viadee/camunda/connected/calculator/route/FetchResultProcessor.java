package de.viadee.camunda.connected.calculator.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class FetchResultProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        String calculationId = exchange.getProperty("calculationId", String.class);

        // somehow fetch result from RPA-Framework of choice
        exchange.getIn().setBody(0);

    }
}
