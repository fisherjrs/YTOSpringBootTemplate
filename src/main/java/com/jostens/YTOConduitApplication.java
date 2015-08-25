package com.jostens;

import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;

import com.jostens.config.YTOConfig;
import com.jostens.context.ConduitApplicationContext;

//SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan ... I'm using the three of them explicity to exclude DataSourceAutoConfig.
@Configuration 
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) 
@ComponentScan
public class YTOConduitApplication extends SpringBootServletInitializer{
	
	private static Logger LOG = LoggerFactory.getLogger(YTOConduitApplication.class);
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	application.showBanner(false);
    	application.sources(YTOConduitApplication.class, YTOConfig.class);
    	
    	//application.listeners(new ListenerApplication());    	
    	application.contextClass(ConduitApplicationContext.class);
    	
    	return application;
    }
    
    @Override
	protected WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
    	WebApplicationContext applicationContext = super.createRootApplicationContext(servletContext);
    	LOG.info("application context :: {}",  applicationContext.getClass());
    	return applicationContext;
	}
    
    //It appears the contex class used by SpringBoot for this app is AnnotationConfigEmbeddedWebApplicationContext

	//Keep this for local dev when using the embedded Container.
    public static void main(String[] args) throws Exception {
		SpringApplication application = new SpringApplication(YTOConduitApplication.class);	
        application.setSources(defineConfigSources());
        
        //application.addListeners(new ListenerApplication());
        
        application.setApplicationContextClass(ConduitApplicationContext.class);
        
        //Enable/Disable container restart when saving files.
    	System.setProperty("spring.devtools.restart.enabled", "false");
    	
    	ApplicationContext applicationContext = application.run(args);
    	LOG.info("application context :: {}",  applicationContext.getClass());
		
    }
  
	@PostConstruct
	private void init() {
		LOG.info("Initialized Application.");
	}
	
	private static HashSet<Object> defineConfigSources() {
		HashSet<Object> sources = new HashSet<Object>();
		sources.add(YTOConfig.class);
		return sources;
	}


}
