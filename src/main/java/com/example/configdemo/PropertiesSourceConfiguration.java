package com.example.configdemo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
public class PropertiesSourceConfiguration
{
	// The filename of the properties file
	private final static String CONFIG_FILENAME = "demo.properties";

	@Bean
	static PropertySourcesPlaceholderConfigurer demoProperties()
	{
		PropertySourcesPlaceholderConfigurer config = new PropertySourcesPlaceholderConfigurer();
		List<Resource> resources = new ArrayList<>();
		Path configInEtcFolder = Paths.get(System.getProperty("file.separator")).normalize()
			.resolve(Paths.get("etc", "demo", CONFIG_FILENAME));

		resources.add(new ClassPathResource(CONFIG_FILENAME));
		resources.add(new FileSystemResource(configInEtcFolder.toFile()));

		config.setLocations(resources.toArray(new Resource[0]));
		config.setIgnoreResourceNotFound(true);
		config.setIgnoreUnresolvablePlaceholders(true);
		return config;
	}
}