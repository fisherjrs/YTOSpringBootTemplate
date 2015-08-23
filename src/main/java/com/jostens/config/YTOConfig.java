package com.jostens.config;

import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.AbstractConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class YTOConfig extends AbstractConfiguration {

	private static Logger LOG = LoggerFactory.getLogger(YTOConfig.class);
	
	public YTOConfig() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	private void init() {
		LOG.info("Initialized YTOConfig.");
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
