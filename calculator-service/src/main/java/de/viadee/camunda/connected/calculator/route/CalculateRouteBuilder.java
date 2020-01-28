package de.viadee.camunda.connected.calculator.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculateRouteBuilder extends RouteBuilder {

    @Autowired
    private TriggerRobotProcessor triggerRobotProcessor;

    @Autowired
    private FetchResultProcessor fetchResultProcessor;

    @Autowired
    private FetchExternalTaskJobProcessor fetchJobsProcessor;

    @Autowired
    private ExecuteExternalTaskJobProcessor executeJobProcessor;

    @Autowired
    private MessageCorrelationProcessor messageCorrelationProcessor;


    @Override
    public void configure() throws Exception {

        from("direct:startRpaCalculation").routeId("rpaCalculationRoute")
                .process(triggerRobotProcessor)
                .process(fetchResultProcessor)
        .log("Result: ${body}");

        from("seda:asyncCalc?waitForTaskToComplete=Never").routeId("messageCorrelationRoute")
                .delayer(2000)
                .log("Request ${body}")
                .process(messageCorrelationProcessor);

       from("timer:calculationJobPoller?period=20s").routeId("externalTaskRoute")
                .process(fetchJobsProcessor)
                    .split(simple("${body}")).process(executeJobProcessor)
                .end();
    }
}
