package de.viadee.camunda.connected.businessprocess;

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.init;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.camunda.bpm.scenario.ProcessScenario;
import org.camunda.bpm.scenario.Scenario;
import org.camunda.bpm.scenario.run.ProcessRunner.ExecutableRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@ExtendWith(ProcessEngineExtension.class)
public class ProcessScenarioTest {

    private static final String PROCESS_DEFINITION_KEY = "taschenrechner";

    @Autowired
    private ProcessEngine processEngine;

    static {
        LogFactory.useSlf4jLogging(); // MyBatis
    }

    @BeforeEach
    public void setup() {
        init(processEngine);
    }

    @Mock
    private ProcessScenario myProcess;

    @Test
    public void testHappyPath() {
        // Define scenarios by using camunda-bpm-assert-scenario:

        ExecutableRunner starter = Scenario.run(myProcess).startByKey(PROCESS_DEFINITION_KEY);
        assertNotNull(starter);

        // when(myProcess.waitsAtReceiveTask(anyString())).thenReturn((messageSubscription) -> {
        //  messageSubscription.receive();
        // });
        // when(myProcess.waitsAtUserTask(anyString())).thenReturn((task) -> {
        //  task.complete();
        // });

        // OK - everything prepared - let's go and execute the scenario
        //Scenario scenario = starter.execute();

        // now you can do some assertions
        //verify(myProcess).hasFinished("EndEvent");
    }

}
