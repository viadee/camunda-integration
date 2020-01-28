package de.viadee.camunda.connected.calculator.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.camunda.bpm.engine.rest.dto.VariableValueDto;
import org.camunda.bpm.engine.rest.dto.externaltask.CompleteExternalTaskDto;
import org.camunda.bpm.engine.rest.dto.externaltask.LockedExternalTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import de.viadee.camunda.connected.calculator.service.CalculatorService;

import java.util.HashMap;

@Component
public class ExecuteExternalTaskJobProcessor implements Processor {

    @Autowired
    private RestTemplate template;

    @Autowired
    private CalculatorService calculatorService;

    @Value("${camunda.rest.baseurl}")
    private String baseUrl;

    @Value("${camunda.externalTask.workerId}")
    private String workerId;

    @Override
    public void process(Exchange exchange) throws Exception {
        LockedExternalTaskDto externalTaskDto = exchange.getIn().getBody(LockedExternalTaskDto.class);

        VariableValueDto num1 = externalTaskDto.getVariables().get("num1");
        VariableValueDto num2 = externalTaskDto.getVariables().get("num2");
        VariableValueDto operator = externalTaskDto.getVariables().get("operator");
        Number result = calculatorService.calculate((int) num1.getValue(), (int) num2.getValue(), (String) operator.getValue());

        completeExternalTask(externalTaskDto, result);

    }

    private void completeExternalTask(LockedExternalTaskDto externalTaskDto, Number result) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        CompleteExternalTaskDto completeExternalTaskDto = new CompleteExternalTaskDto();
        completeExternalTaskDto.setWorkerId(workerId);

        HashMap<String, VariableValueDto> variables = new HashMap<>();
        VariableValueDto value = new VariableValueDto();
        value.setValue(result);
        value.setType("Long");
        variables.put("result", value);
        completeExternalTaskDto.setVariables(variables);

        HttpEntity<CompleteExternalTaskDto> requestEntity = new HttpEntity<>(completeExternalTaskDto,headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "external-task/{taskId}/complete");
        template.exchange(builder.buildAndExpand(externalTaskDto.getId()).toUriString(), HttpMethod.POST, requestEntity, Object.class);
    }

}
