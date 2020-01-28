package de.viadee.camunda.connected.businessprocess;

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.init;
import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ProcessTest {

    private static final String PROCESS_DEFINITION_KEY = "taschenrechner";

    @Autowired
    private ProcessEngine processEngine;

    static {
        LogFactory.useSlf4jLogging();
    }

    @Before
    public void setup() {
        init(processEngine);
    }

    @Test
    public void testHappyPath() {
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
        assertNotNull(processInstance);
        // Now: Drive the process by API and assert correct behavior by camunda-bpm-assert
    }

}
