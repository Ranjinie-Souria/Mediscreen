package com.mediscreen;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.mediscreen")
public class CustomProperties {

    private String apiPatient;
	public String getApiPatient() {
		return apiPatient;
	}
	public void setApiPatient(String apiPatient) {
		this.apiPatient = apiPatient;
	}
    
    

}