package com.spring.development.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.Map;

@Configuration
public class RedisConfiguration {

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		// Configure ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.activateDefaultTyping(
			LaissezFaireSubTypeValidator.instance,
			ObjectMapper.DefaultTyping.NON_FINAL,
			JsonTypeInfo.As.WRAPPER_ARRAY
		);

		// Configure Jackson2JsonRedisSerializer with the ObjectMapper
		Jackson2JsonRedisSerializer<Map> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, Map.class);

		// Configure StringRedisTemplate
		StringRedisTemplate template = new StringRedisTemplate(factory);
		template.setValueSerializer(jsonRedisSerializer);
		template.setHashKeySerializer(jsonRedisSerializer);
		template.setHashValueSerializer(jsonRedisSerializer);

		template.afterPropertiesSet();
		return template;
	}
}
