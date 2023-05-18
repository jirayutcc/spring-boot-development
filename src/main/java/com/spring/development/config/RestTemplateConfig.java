package com.spring.development.config;

import com.spring.development.properties.RestClientProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

	private final RestClientProperty restClientProperty;

	@Bean
	HttpHeaders httpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		return headers;
	}

	@Bean
	RestTemplate client(SimpleClientHttpRequestFactory simpleClientHttpRequestFactory) {
		return new RestTemplate(simpleClientHttpRequestFactory);
	}

	@Bean
	SimpleClientHttpRequestFactory factory() {
		SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		simpleClientHttpRequestFactory.setReadTimeout(restClientProperty.getRestTemplate().getTimeOut());
		simpleClientHttpRequestFactory.setConnectTimeout(restClientProperty.getRestTemplate().getTimeOut());
		return simpleClientHttpRequestFactory;
	}
}
