package com.jostens.ytoconduit.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
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
	
	
    @RequestMapping("/properties")
    public String showProperties(
    		@RequestParam(value="name", required=false, defaultValue="World") String name,
    		@RequestParam(value="designId", required=false, defaultValue="9") String designId,
    		Model model) {
        model.addAttribute("name", name);
        LOG.info("configurer :: " + configurer.getAppliedPropertySources().get("application.yml"));
        YTODesignDefinition designDefinition = definitionsService.getDesignDefinition(Long.valueOf(designId));
        model.addAttribute("designDefinition", designDefinition);
        return "greeting";
    }


}
