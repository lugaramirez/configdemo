package com.example.configdemo;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@PropertySource(value = "file:///C:/etc/demo/demo.properties", ignoreResourceNotFound = true)
@EnableRedisHttpSession
public class DemoConfig
{
	private final Environment environment;

	public DemoConfig(Environment environment)
	{
		this.environment = environment;
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory()
	{
		String hostName = environment.getProperty("redis.host");
		int port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("redis.port")));
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(hostName, port);
		return new LettuceConnectionFactory(configuration);
	}
}
