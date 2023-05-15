package com.spring.development.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationPropertiesScan
@ConfigurationProperties("application")
public class ApplicationProperty {

    private String version;
}
