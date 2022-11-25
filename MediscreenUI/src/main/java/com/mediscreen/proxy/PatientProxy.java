package com.mediscreen.proxy;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mediscreen.CustomProperties;
import com.mediscreen.model.Patient;

@Component
public class PatientProxy {
	
	private static final Logger log = LogManager.getLogger(PatientProxy.class);

	@Autowired
	private CustomProperties props;
	
	/**
	 * Get all Patients
	 * @return List of all Patients
	 */
	public List<Patient> getPatients() {
		String apiUrl = props.getApiPatient();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Patient>> response = restTemplate.exchange(
				apiUrl,
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<List<Patient>>() {}
			);
		
		log.debug("Get Patients call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Get a Patient by the id
	 * @param id The id of the Patient
	 * @return The Patient
	 */
	public Patient getPatient(int id) {
		String apiUrl = props.getApiPatient();
		String getPatientUrl = apiUrl + "/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Patient> response = restTemplate.exchange(
				getPatientUrl, 
				HttpMethod.GET, 
				null,
				Patient.class
			);
		
		log.debug("Get Patient : " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Add a new Patient 
	 * @param patient A new Patient (without an id)
	 * @return The Patient full filled (with an id)
	 */
	public Patient createPatient(Patient patient) {
		String apiUrl = props.getApiPatient();
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Patient> request = new HttpEntity<Patient>(patient);
		ResponseEntity<Patient> response = restTemplate.exchange(
				apiUrl, 
				HttpMethod.POST, 
				request, 
				Patient.class);
		
		log.debug("Create Patient :  " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Update a patient
	 * @param patient Existing patient to update
	 */
	public Patient updatePatient(Patient patient) {
		String apiUrl = props.getApiPatient();
		String updatePatientUrl = apiUrl + "/" + patient.getPatientId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Patient> request = new HttpEntity<Patient>(patient);
		ResponseEntity<Patient> response = restTemplate.exchange(
				updatePatientUrl, 
				HttpMethod.PUT, 
				request, 
				Patient.class);
		
		log.debug("Update Employee call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Delete a patient
	 * @param id The patient's id to delete
	 */
	public void deletePatient(int id) {
		String apiUrl = props.getApiPatient();
		String deletePatientUrl = apiUrl + "/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deletePatientUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Patient :  " + response.getStatusCode().toString());
	}

}