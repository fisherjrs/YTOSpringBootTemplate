package com.jostens.config;

import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.AbstractConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import service.DefinitionsService;
import service.TransferService;

@Configuration
@ComponentScan
public class YTOConfig extends AbstractConfiguration {

	private static Logger LOG = LoggerFactory.getLogger(YTOConfig.class);
	
	@Autowired
	private Environment env;
	
	public YTOConfig() {
		// TODO Auto-generated constructor stub
	}
	
	@Bean
    public DefinitionsService definitionsService() {
        return new DefinitionsService();
    }
	
	@PostConstruct
	private void init() {
		LOG.info("Initialized YTOConfig.");
		if (env instanceof ConfigurableEnvironment) {
			LOG.info("We have an environment that is ConfigurableEnvironment.");
			LOG.info("Profiles :: " + env.getActiveProfiles());
			LOG.info("Property :: " + env.getProperty("spring.profiles.active"));
			/*
			String resource = env.getProperty("spring.profiles.sub") +".main.properties";
		        Resource props = new ClassPathResource(resource);
		        if (env instanceof ConfigurableEnvironment && props.exists()) {
		            MutablePropertySources sources = ((ConfigurableEnvironment) env).getPropertySources();
		            sources.addBefore("main", new ResourcePropertySource(props)); 
		        }
		    */
		} else {
			LOG.info("Not a configurable environment.");
		}
	}
	
	public Integer getPosition() {
		return Integer.valueOf(44);
	}
	
	@Override
	public Object getProperty(String key) {
		return null;
	}
	
	@Override
	public boolean containsKey(String key) {
		return getProperty(key) != null;
	}

	@Override
	public Iterator<String> getKeys() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return getKeys().hasNext();
	}
	
	@Override
	protected void addPropertyDirect(String key, Object value) {
		
	}

}
