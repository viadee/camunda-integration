package de.viadee.camunda.connected.calculator.route;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.camunda.bpm.engine.rest.dto.externaltask.FetchExternalTasksDto;
import org.camunda.bpm.engine.rest.dto.externaltask.LockedExternalTaskDto;
import org.camunda.bpm.engine.rest.dto.message.CorrelationMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FetchExternalTaskJobProcessor implements Processor {

    @Autowired
    private RestTemplate template;

    @Value("${camunda.rest.baseurl}")
    private String baseUrl;

    @Value("${camunda.externalTask.workerId}")
    private String workerId;


    @Override
    public void process(Exchange exchange) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ArrayList<String> variables = new ArrayList<>();
        variables.add("num1");
        variables.add("num2");
        variables.add("operator");

        FetchExternalTasksDto request = createExternalTaskRequest("calculator",variables);

        HttpEntity<FetchExternalTasksDto> requestEntity = new HttpEntity<>(request,headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "external-task/fetchAndLock");
        ResponseEntity<LockedExternalTaskDto[]> result = template.exchange(builder.toUriString(), HttpMethod.POST, requestEntity, LockedExternalTaskDto[].class);
        LockedExternalTaskDto[] fetchExternalTasksDtos = result.getBody();
        List<LockedExternalTaskDto> resultlist = Arrays.asList(fetchExternalTasksDtos);
        exchange.getIn().setBody(resultlist);
    }

    private FetchExternalTasksDto createExternalTaskRequest(String topic, ArrayList<String> variables) {
        FetchExternalTasksDto request = new FetchExternalTasksDto();
        request.setWorkerId(workerId);
        request.setMaxTasks(2);
        FetchExternalTasksDto.FetchExternalTaskTopicDto fetchExternalTaskTopicDto = new FetchExternalTasksDto.FetchExternalTaskTopicDto();
        fetchExternalTaskTopicDto.setTopicName(topic);
        fetchExternalTaskTopicDto.setLockDuration(10000);

        fetchExternalTaskTopicDto.setVariables(variables);
        ArrayList<FetchExternalTasksDto.FetchExternalTaskTopicDto> topics = new ArrayList<>();
        topics.add(fetchExternalTaskTopicDto);
        request.setTopics(topics);
        return request;
    }
}
