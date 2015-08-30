package org.domate.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationPath("")
public class DomateApplication extends ResourceConfig {
	

	private final static Logger logger = LoggerFactory.getLogger(DomateApplication.class);
	
	public DomateApplication() {
		logger.info("setting up REST resources");
		packages("org.domate.resources");
	}

}
