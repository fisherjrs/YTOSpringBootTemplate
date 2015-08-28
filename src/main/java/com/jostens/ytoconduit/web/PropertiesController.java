package com.jostens.ytoconduit.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.DefinitionsService;

import com.jostens.config.YTOConfig;
import com.jostens.model.YTODesignDefinition;

@Controller
@RequestMapping("/conduitservices")
public class PropertiesController {
	private static Logger LOG = LoggerFactory.getLogger(PropertiesController.class);
	
	@Autowired
	YTOConfig ytoConfig;
	@Autowired
	private ApplicationContext context;
	@Autowired
	private DefinitionsService definitionsService;
	@Autowired
	private PropertySourcesPlaceholderConfigurer configurer;
	@Autowired
	private Environment env;
	
	
    @RequestMapping("/properties")
    public String showProperties(
    		@RequestParam(value="name", required=false, defaultValue="World") String name,
    		@RequestParam(value="designId", required=false, defaultValue="9") String designId,
    		Model model) {
        
    	if (env instanceof ConfigurableEnvironment) {
			LOG.info("We have an environment that is ConfigurableEnvironment.");
			LOG.info("Property :: " + env.getProperty("spring.profiles.active"));
			for (String profile : env.getActiveProfiles()) {
				LOG.info("profile :: " + profile);
			}
			
			for (String profile : env.getDefaultProfiles()) {
				LOG.info("defaultprofile :: " + profile);
			}

			for (org.springframework.core.env.PropertySource<?> propertySource : ((ConfigurableEnvironment) env).getPropertySources() ) {
				LOG.info("property source " + propertySource.getName());
			}
			
			Map<String,Object> properties = ((ConfigurableEnvironment) env).getSystemProperties();
			for(Map.Entry<String,Object> item : properties.entrySet()) {
				LOG.info("property map :: " + item.getKey() + " ::: " + item.getValue());
			}
			
			Map<String,Object> systemEnv = ((ConfigurableEnvironment) env).getSystemEnvironment();
			for(Map.Entry<String,Object> item : systemEnv.entrySet()) {
				LOG.info("property map :: " + item.getKey() + " ::: " + item.getValue());
			}
			
			
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
    	
    	
        LOG.info("configurer :: " + configurer.getAppliedPropertySources().get("application.yml"));
        
        
        model.addAttribute("name", name);
        YTODesignDefinition designDefinition = definitionsService.getDesignDefinition(Long.valueOf(designId));
        model.addAttribute("designDefinition", designDefinition);
        
        
        
        return "greeting";
    }


}
