package com.spring.development.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationPropertiesScan
@ConfigurationProperties("rest-client")
public class RestClientProperty {

    private RestTemplateConfig restTemplate;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class RestTemplateConfig {
		private Integer timeOut;
	}
}
