package com.spring.development.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(
	entityManagerFactoryRef = "postgresqlEntityManagerFactory",
	transactionManagerRef = "postgresqlTransactionManager"
)
@EnableTransactionManagement
public class PostgreSqlDataSourceConfiguration {

	@Primary
	@Bean("postgresqlProperties")
	@ConfigurationProperties("data-source.postgresql")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean("postgresqlDatasource")
	public DataSource dataSource(
		@Qualifier("postgresqlProperties") DataSourceProperties properties
	) {
		return properties
			.initializeDataSourceBuilder()
			.build();
	}

	@Primary
	@Bean("postgresqlEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
		EntityManagerFactoryBuilder builder,
		@Qualifier("postgresqlDatasource") DataSource dataSource
	) {
		return builder
			.dataSource(dataSource)
			.packages("com.spring.development.model.postgresql")
			.build();
	}

	@Bean(name = "postgresqlTransactionManager")
	public PlatformTransactionManager platformTransactionManager(
		@Qualifier("postgresqlEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory
	) {
		return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
	}
}
