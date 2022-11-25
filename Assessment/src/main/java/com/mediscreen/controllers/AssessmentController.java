package com.mediscreen.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.model.Assessment;
import com.mediscreen.model.Patient;
import com.mediscreen.services.AssessmentService;
import com.mediscreen.services.PatientService;


@RestController
public class AssessmentController {
	
	@Autowired
	private AssessmentService aService;
	@Autowired
	private PatientService patientService;
	
	/**
	 * Show Assessment for the patient id
	 * @return assessment results
	 */
	@GetMapping("/assess/{id}")
    public Assessment getAssessment(@PathVariable int id)
    {
		Patient p = patientService.getPatient(id);
		Assessment assessment = aService.getAssessmentForPatientId(p.getPatientId());
		
		return assessment;
    }
	
	/**
	 * Show Assessment by patient family name
	 * @return assessment results
	 */
	@GetMapping("/assess/familyName/{familyName}")
    public List<String> getAssessmentByFamilyName(@PathVariable String familyName)
    {
		List<Patient> patients = patientService.getPatientsByFamilyName(familyName);
		List<String> stringsToReturn = new ArrayList<String>();
		for(Patient p : patients) {
			Assessment assessment = aService.getAssessmentForPatientId(p.getPatientId());
			String stringToReturn = "Patient: "+p.getFirstName()+" "+p.getFamilyName()+
					" (age "+assessment.getPatientAge()+
					") diabetes assessment is: "+assessment.getResult();
			stringsToReturn.add(stringToReturn);
		}
		
		return stringsToReturn;
    }
	
}
