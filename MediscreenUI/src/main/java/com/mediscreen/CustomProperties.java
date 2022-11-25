package com.mediscreen;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.mediscreen")
public class CustomProperties {

    private String apiPatient;
    private String apiAssessment;
    private String apiNote;

	public String getApiPatient() {
		return apiPatient;
	}
	public void setApiPatient(String apiPatient) {
		this.apiPatient = apiPatient;
	}
	public String getApiNote() {
		return apiNote;
	}
	public void setApiNote(String apiNote) {
		this.apiNote = apiNote;
	}
	public String getApiAssessment() {
		return apiAssessment;
	}
	public void setApiAssessment(String apiAssessment) {
		this.apiAssessment = apiAssessment;
	}
	
	
    
    
    

}