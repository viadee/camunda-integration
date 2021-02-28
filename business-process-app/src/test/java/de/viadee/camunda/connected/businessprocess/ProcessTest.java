package de.viadee.camunda.connected.businessprocess;

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.init;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@ExtendWith(ProcessEngineExtension.class)
public class ProcessTest {

    private static final String PROCESS_DEFINITION_KEY = "taschenrechner";

    @Autowired
    private ProcessEngine processEngine;

    static {
        LogFactory.useSlf4jLogging();
    }

    @BeforeEach
    public void setup() {
        init(processEngine);
    }

    @Test
    @Deployment(resources = {"process.bpmn"})
    public void testHappyPath() {
      
        ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
        assertNotNull(processInstance);
        // Now: Drive the process by API and assert correct behavior by camunda-bpm-assert
    }

}
