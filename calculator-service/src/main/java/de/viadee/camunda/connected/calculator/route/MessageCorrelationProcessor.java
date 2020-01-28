package de.viadee.camunda.connected.calculator.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.camunda.bpm.engine.rest.dto.VariableValueDto;
import org.camunda.bpm.engine.rest.dto.message.CorrelationMessageDto;
import org.camunda.bpm.engine.rest.dto.message.MessageCorrelationResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import de.viadee.camunda.connected.calculator.model.CallbackCalculateModel;
import de.viadee.camunda.connected.calculator.service.CalculatorService;

import java.util.HashMap;

@Component
public class MessageCorrelationProcessor implements Processor {

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private RestTemplate template;

    @Value("${camunda.rest.baseurl}")
    private String baseUrl;

    @Override
    public void process(Exchange exchange) throws Exception {
        final CallbackCalculateModel body = exchange.getIn().getBody(CallbackCalculateModel.class);

        final Number result = calculatorService.calculate(body.getNum1(), body.getNum2(), body.getOperator());

        CorrelationMessageDto correlationMessageDto = createCorrelationMessage(body.getCorrelationId(),result);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "message");
        template.postForObject(builder.toUriString(),correlationMessageDto, MessageCorrelationResultDto.class);
    }

    private CorrelationMessageDto createCorrelationMessage(String correlationId, Number calculationResult) {
        CorrelationMessageDto result = new CorrelationMessageDto();
        result.setMessageName("calculationResultMessage");
        VariableValueDto correlationIdValue = new VariableValueDto();
        correlationIdValue.setType("String");
        correlationIdValue.setValue(correlationId);
        result.setCorrelationKeys(new HashMap<>());
        result.getCorrelationKeys().put("correlationId", correlationIdValue);

        VariableValueDto calculationResultValue = new VariableValueDto();
        calculationResultValue.setType("String");
        calculationResultValue.setValue(calculationResult.toString());
        result.setProcessVariables(new HashMap<>());
        result.getProcessVariables().put("result", calculationResultValue);
        return result;
    }
}
