package com.mediscreen.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mediscreen.model.Assessment;
import com.mediscreen.model.Note;
import com.mediscreen.model.Patient;
import com.mediscreen.services.AssessmentService;
import com.mediscreen.services.NoteService;
import com.mediscreen.services.PatientService;

@RestController
public class PatientController {
	
	@Autowired
	PatientService service;
	
	@Autowired
	NoteService noteService;
	
	@Autowired
	AssessmentService assessService;
	
	
	
	@GetMapping("/patients")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("patients/list");
		List<Patient> listPatients = service.getPatients();
		mv.addObject("patients", listPatients);
		return mv;
	}
	
	@GetMapping("/patients/{id}")
	public ModelAndView showOne(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("patients/show");
		Patient patient = service.getPatient(id);
		List<Note> notes = noteService.getNotesForPatient(id);	
		Assessment assess = assessService.getAssessment(id);
		String assessment = "Patient: "+patient.getFirstName()+" "+patient.getFamilyName()+
				" (age "+assess.getPatientAge()+
				") diabetes assessment is: "+assess.getResult();
		
		mv.addObject("patient", patient);
		mv.addObject("name", patient.getFirstName()+" "+patient.getFamilyName());
		mv.addObject("notes", notes);
		mv.addObject("assessment", assessment);
		return mv;		
	}
	
	@GetMapping("/patients/add")
	public ModelAndView createPatient(Patient patient) {
		ModelAndView mv = new ModelAndView("patients/add");
		Patient p = new Patient();
		mv.addObject("patient", p);
		return mv;
	}
	
	@GetMapping("/patients/update/{id}")
	public ModelAndView updatePatient(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("patients/edit");
		Patient patient = service.getPatient(id);		
		mv.addObject("patient", patient);	
		return mv;		
	}
	
	@GetMapping("/patients/delete/{id}")
	public ModelAndView deleteEmployee(@PathVariable int id) {
		service.deletePatient(id);
		return new ModelAndView("redirect:/patients?delete");		
	}
	
	@PostMapping("/savePatient")
	public ModelAndView savePatient(Integer patientId,String familyName,String firstName,Date birthdate,String gender,String address,String phone) {
		service.savePatient(new Patient(patientId,familyName,firstName,birthdate,gender,address,phone));
		return new ModelAndView("redirect:/patients?success");	
	}

}
