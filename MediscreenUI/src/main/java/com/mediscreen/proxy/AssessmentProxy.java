package com.mediscreen.proxy;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mediscreen.CustomProperties;
import com.mediscreen.model.Assessment;

@Component
public class AssessmentProxy {
	
	private static final Logger log = LogManager.getLogger(AssessmentProxy.class);

	@Autowired
	private CustomProperties props;
	
	/**
	 * Get the assessment for the patient id
	 * @return The assessment
	 */
	public Assessment getAssessment(int id){
		String apiUrl = props.getApiAssessment()+"/assess/"+id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Assessment> response = restTemplate.exchange(
				apiUrl, 
				HttpMethod.GET, 
				null,
				Assessment.class
			);
		
		log.debug("Get Assessment call " + response.getStatusCode().toString());

		return response.getBody();
	}
	
	/**
	 * Get the assessments for the familyname
	 * @return The list of assessments
	 */
	public List<Assessment> getAssessments(String familyName){
		String apiUrl = props.getApiAssessment() + "/assess/familyName/"+familyName;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Assessment>> response = restTemplate.exchange(
				apiUrl,
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<List<Assessment>>() {}
			);
		
		log.debug("Get Assessments call " + response.getStatusCode().toString());

		return response.getBody();
	}
}
