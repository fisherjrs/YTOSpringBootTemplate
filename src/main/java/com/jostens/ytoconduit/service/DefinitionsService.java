package com.jostens.ytoconduit.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;

import com.jostens.ytoconduit.model.YTODesignDefinition;
import com.jostens.ytoconduit.model.YTOImageDefinition;

@Service
public class DefinitionsService {

	public DefinitionsService() {
		// TODO Auto-generated constructor stub
	}

	public String getServiceDetails() {
		return "This is a placeholder service class and function that is used for wiring up a configuration.";
	}
	
public YTODesignDefinition getDesignDefinition(Long designId) {
		
		YTOImageDefinition imageDef = new YTOImageDefinition();
		imageDef.setIndex(0);
		imageDef.setImageId(Long.valueOf(423842034));
		imageDef.setImageName("Barny");
		imageDef.setUrl("http://testyearbookavenue.jostens.com/services/getimage?locator=TRY1-9-5005416743&quality=thumbnail");
		
		YTOImageDefinition imageDef2 = new YTOImageDefinition();
		imageDef2.setIndex(1);
		imageDef2.setImageId(Long.valueOf(11111111));
		imageDef2.setImageName("Wilma");
		imageDef2.setUrl("http://testyearbookavenue.jostens.com/services/getimage?locator=TRY1-9-5005416743&quality=thumbnail");
		
		List<YTOImageDefinition> imageList = new ArrayList<YTOImageDefinition>();
		imageList.add(imageDef);
		imageList.add(imageDef2);
		
		YTODesignDefinition designDef = new YTODesignDefinition();
		designDef.setIndex(0);
		designDef.setDesignId(designId);
		designDef.setImageDefinition(imageList);
		
		/*
		List<YTOImageDefinition> imageDefList = conduitDao.getImageDefinitions(designId);
		for(YTOImageDefinition image : imageDefList) {
			try {
				URI uri = createImageURI(designId, image.getImageId(), "thumbnail");
				image.setUrl(uri.toString());
			} catch(URISyntaxException e) {
				LOG.info("Unable to set the url for image in ConduitService", e);
			}
		}
		
		designDef.setImageDefinition(imageDefList);
		*/
		return designDef;
	}
	
	private URI createImageURI(Long designId, Long imageId, String quality) throws URISyntaxException {		
		URIBuilder uriBuilder = new URIBuilder();
		//uriBuilder.setScheme(getSchemeFromServiceUrl(configuration.getString(CommonConfigConstants.DATA_SERVICES_URL)));
		//uriBuilder.setHost(getHostFromServiceUrl(configuration.getString(CommonConfigConstants.DATA_SERVICES_URL)));
		uriBuilder.setPath("/services/getimage");
		uriBuilder.setParameter("locator", "TRY1-" + designId + "-" + imageId);
		uriBuilder.setParameter("quality", quality);
		
		return uriBuilder.build();
	}

	private String getSchemeFromServiceUrl(String url) {
		int position = url.indexOf(":");
		String scheme = url.substring(0, position);
		return scheme;
	}

	private String getHostFromServiceUrl(String url) {
		int position = url.indexOf(":");
		String host = url.substring(position + 3);
		return host;
	}	
}
