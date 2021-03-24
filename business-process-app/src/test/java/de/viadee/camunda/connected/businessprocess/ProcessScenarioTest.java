package de.viadee.camunda.connected.businessprocess;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Map;

import de.viadee.camunda.connected.businessprocess.calculator.Operator;
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
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@ExtendWith(ProcessEngineExtension.class)
@SpringBootTest
public class ProcessScenarioTest {

    private static final String PROCESS_DEFINITION_KEY = "taschenrechner";

    static {
        LogFactory.useSlf4jLogging(); // MyBatis
    }
    
    @BeforeEach
    public void setup() {
      MockitoAnnotations.initMocks(this);
     when(myScenario.waitsAtUserTask("utErgebnisBestaetigen")).thenReturn((task) -> {task.complete();});
    }
    
    @Mock private ProcessScenario myScenario;
    
    @Test
    @Deployment(resources = {"process.bpmn"})
    public void testPlainDelegateIntegration() {

        Map<String, Object> variables = 
            Variables.createVariables()
          .putValue("integrationType", "plain")
          .putValue("num1", Long.valueOf("10"))
          .putValue("num2", Long.valueOf("10"))
          .putValue("operator", Operator.Plus.toString());
      
        Scenario scenario = Scenario.run(myScenario).startByKey(PROCESS_DEFINITION_KEY, variables).execute();
        assertNotNull(scenario);

        // Define scenarios by using camunda-bpm-assert-scenario:
        assertThat(scenario.instance(myScenario)).variables().containsEntry("result", Long.valueOf("20"));
        verify(myScenario).hasFinished("stCalculatePlain");
        verify(myScenario, times(1)).waitsAtUserTask("utErgebnisBestaetigen");
    }

}
