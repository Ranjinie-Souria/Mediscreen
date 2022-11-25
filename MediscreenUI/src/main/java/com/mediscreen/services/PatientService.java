package com.mediscreen.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mediscreen.model.Patient;
import com.mediscreen.proxy.PatientProxy;


@Service
public class PatientService {
	
	@Autowired
	private PatientProxy proxy;
	
	public List<Patient> getPatients() {
		return proxy.getPatients();
	}
	
	public Patient getPatient(int id) {
		return proxy.getPatient(id);
	}
	
	public Patient savePatient(Patient patient) {
		Patient savedPatient;
        if(patient.getPatientId() == null) {
        	savedPatient = proxy.createPatient(patient);
        } else {
        	savedPatient = proxy.updatePatient(patient);
        }
        return savedPatient;
	}
	
	public void deletePatient(int id) {
		proxy.deletePatient(id);
	}
	
	
	

}
