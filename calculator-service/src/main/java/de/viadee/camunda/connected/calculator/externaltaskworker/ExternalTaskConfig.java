package de.viadee.camunda.connected.calculator.externaltaskworker;

import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalTaskConfig {
	
	@Value("${camunda.externalTask.asyncResponseTimeout}")
	private int asyncResponseTimeout;
	
	@Value("${camunda.rest.baseurl}")
	private String baseUrl;
	
	@Bean
	public ExternalTaskClient externalTaskClient() {
		ExternalTaskClient client =  ExternalTaskClient.create().baseUrl(this.baseUrl)
		.asyncResponseTimeout(this.asyncResponseTimeout).build();
		return client;
	}
	
}
