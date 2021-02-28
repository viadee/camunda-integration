package de.viadee.camunda.connected.businessprocess;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Map;
import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.camunda.bpm.scenario.ProcessScenario;
import org.camunda.bpm.scenario.Scenario;
import org.camunda.bpm.scenario.run.ProcessRunner.ExecutableRunner.StartingByKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@ExtendWith(ProcessEngineExtension.class)
public class ProcessScenarioTest {

    private static final String PROCESS_DEFINITION_KEY = "taschenrechner";

    static {
        LogFactory.useSlf4jLogging(); // MyBatis
    }
    
    @BeforeEach
    public void setup() {
      MockitoAnnotations.initMocks(this);
    }
    
    @Mock private ProcessScenario myScenario;
    
    @Test
    @Deployment(resources = {"process.bpmn"})
    public void testPlainDelegateIntegration() {

        Map<String, Object> variables = 
            Variables.createVariables()
          .putValue("integrationType", "plain")
          .putValue("num1", "10")
          .putValue("num2", "10")
          .putValue("operator", "+");
      
        StartingByKey starter = Scenario.run(myScenario).startByKey(PROCESS_DEFINITION_KEY, variables);
        assertNotNull(starter);
        
        // Define scenarios by using camunda-bpm-assert-scenario:

        // Scenario scenario = starter.execute();
        // assertThat(scenario.instance(myScenario)).variables().containsEntry("result", "20");
        // verify(myScenario, never()).hasStarted("SubProcessManualCheck");
        // verify(myScenario).hasFinished("EndEventApplicationAccepted");
    }

}
