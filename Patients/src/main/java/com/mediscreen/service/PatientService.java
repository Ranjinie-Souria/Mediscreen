package com.mediscreen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.model.Patient;
import com.mediscreen.repository.IPatientRepository;

@Service
public class PatientService {
	
	@Autowired
	private IPatientRepository patientRepository;
	
	public List<Patient> getPatients() {
		return patientRepository.findAll();
	}
	
	public Optional<Patient> getPatientById(Integer id) {
		return patientRepository.findById(id);
	}
	
	public Patient savePatient(Patient patient) {
		patientRepository.save(patient);
		return patient;		
	}
	
	public void deletePatientById(Integer id) {
		patientRepository.deleteById(id);
	}
	
	public List<Patient> getPatientsByAddress(String address) {
		return patientRepository.findByAddress(address);
	}
	
	public List<Patient> getPatientsByFamilyName(String familyName) {
		return patientRepository.findByFamilyName(familyName);
	}

}
