package com.spring.development.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(
	entityManagerFactoryRef = "mariadbEntityManagerFactory",
	transactionManagerRef = "mariadbTransactionManager"
)
@EnableTransactionManagement
public class MariaDbDataSourceConfiguration {

	@Bean("mariadbProperties")
	@ConfigurationProperties("data-source.postgresql")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean("mariadbDatasource")
	public DataSource dataSource(
		@Qualifier("postgresqlProperties") DataSourceProperties properties
	) {
		return properties
			.initializeDataSourceBuilder()
			.build();
	}

	@Bean("mariadbEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
		EntityManagerFactoryBuilder builder,
		@Qualifier("mariadbDatasource") DataSource dataSource
	) {
		return builder
			.dataSource(dataSource)
			.packages("com.spring.development.model.mariadb")
			.build();
	}

	@Bean(name = "mariadbTransactionManager")
	public PlatformTransactionManager platformTransactionManager(
		@Qualifier("mariadbEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory
	) {
		return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
	}
}
