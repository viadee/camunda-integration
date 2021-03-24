package de.viadee.camunda.connected.calculator;

import de.viadee.camunda.connected.calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CalculatorServiceApplicationTest {

  @Autowired
  private CalculatorService service;

  @Test
  public void testContextLoads() {
    assertNotNull(service);
  }


}
