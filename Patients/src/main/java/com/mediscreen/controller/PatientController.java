package com.mediscreen.controller;

import java.net.URI;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mediscreen.exceptions.PatientNotFoundException;
import com.mediscreen.model.Patient;
import com.mediscreen.service.PatientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("API for CRUD operations on the Patients")

@RestController
public class PatientController {

	@Autowired
	private PatientService patientService;

	
	/**
	 * Shows all patients
	 * @return all patients
	 */
	@ApiOperation(value = "Returns all patients stored in the database")
	@GetMapping("/patients")
    public List<Patient> getPatients()
    {
    	return patientService.getPatients();
    }
	
	/**
	 * Shows one patient
	 * @return a patient
	 */
	@ApiOperation(value = "Returns a patient with the selected id")
	@GetMapping("/patients/{id}")
    public Patient getPatient(@PathVariable int id)
    {
		Optional<Patient> patient = patientService.getPatientById(id);
	      if(patient.isEmpty()) {
	    	  throw new PatientNotFoundException("The patient with id " + id + " does not exist.");
	      }
    	return patient.get();
    }
	
	/**
	 * Shows all patients with family name
	 * @return list of patients
	 */
	@ApiOperation(value = "Returns a list of patients with the family name")
	@GetMapping("/patients/familyName/{familyName}")
    public List<Patient> getPatientByFamilyName(@PathVariable String familyName)
    {
		List<Patient> patients = patientService.getPatientsByFamilyName(familyName);
    	return patients;
    }
    
	/**
	 * Create - Adds the new patient
	 * @param patient The new patient
	 * @return ResponseEntity
	 */
    @PostMapping("/patients")
    @ApiOperation(value = "Adds a patient with valid fields to the database")
    public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient) {
		Patient patientAdded = patientService.savePatient(patient);
		if (Objects.isNull(patientAdded)) {
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/patients/{id}")
				.buildAndExpand(patientAdded.getPatientId())
				.toUri();
		return ResponseEntity.created(location).build();
    }
    
    /**
	 * Delete - deletes a patient
	 * @param id patient's id
	 */
    @ApiOperation(value = "Deletes the patient with the selected id")
    @DeleteMapping (value = "/patients/{id}")
    public void deletePatient(@PathVariable int id) {
    	patientService.deletePatientById(id);
    }
    
	/**
	 * Update - Update an existing employee
	 * @param id - The id of the employee to update
	 * @param employee - The employee object updated
	 * @return
	 */
	@PutMapping("/patients/{id}")
	@ApiOperation(value = "Updates the patient with the selected id")
	public ResponseEntity<Patient> updatePatient(@PathVariable int id, @RequestBody Patient patient) {
		Optional<Patient> p = patientService.getPatientById(id);
		if(p.isPresent()) {
			Patient currentPatient = p.get();
			
			String firstName = patient.getFirstName();
			if(firstName != null) {
				currentPatient.setFirstName(firstName);
			}
			String lastName = patient.getFamilyName();
			if(lastName != null) {
				currentPatient.setFamilyName(lastName);
			}
			Date birthdate = patient.getBirthdate();
			if(birthdate != null) {
				currentPatient.setBirthdate(birthdate);
			}
			String gender = patient.getGender();
			if(gender != null) {
				currentPatient.setGender(gender);
			}
			String address = patient.getAddress();
			if(address != null) {
				currentPatient.setAddress(address);
			}
			String phone = patient.getPhone();
			if(phone != null) {
				currentPatient.setPhone(phone);
			}
			patientService.savePatient(currentPatient);
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/patients/{id}")
					.buildAndExpand(currentPatient.getPatientId())
					.toUri();
			return ResponseEntity.created(location).build();	

		}
		return ResponseEntity.noContent().build();
	}


}
