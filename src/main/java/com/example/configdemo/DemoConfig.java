package com.example.configdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class DemoConfig
{
	@Bean
	public RedisConnectionFactory redisConnectionFactory(
		@Value("${spring.redis.host:localhost}") String hostName,
		@Value("${spring.redis.port:6379}") int port)
	{
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(hostName, port);
		return new LettuceConnectionFactory(configuration);
	}
}
