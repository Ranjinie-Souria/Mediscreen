package com.mediscreen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.mediscreen.model.Note;
import com.mediscreen.model.Patient;
import com.mediscreen.services.NoteService;
import com.mediscreen.services.PatientService;

@RestController
public class NoteController {
	
	@Autowired
	NoteService nservice;
	
	@Autowired
	PatientService service;
	
	@GetMapping("/notes")
	public ModelAndView home(){
		ModelAndView mv = new ModelAndView("notes/list");
		List<Note> list = nservice.getNotes();
		mv.addObject("notes", list);
		return mv;
	}
	
	@GetMapping("/notes/update/{id}")
	public ModelAndView updateNote(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("patients/edit");
		Patient patient = service.getPatient(id);	
		mv.addObject("patient", patient);	
		return mv;		
	}
	
	@GetMapping("/notes/delete/{id}/{idPatient}")
	public ModelAndView deleteNote(@PathVariable Integer idPatient,@PathVariable String id) {
		nservice.deleteNote(id);
		return new ModelAndView("redirect:/patients/"+idPatient+"?noteDeleted");		
	}
	
	@GetMapping("/notes/add/{idPatient}")
	public ModelAndView createNote(@PathVariable Integer idPatient, Model model) {
		ModelAndView mv = new ModelAndView("notes/add");
		Note note = new Note();
		note.setPatient(idPatient);
		mv.addObject("note", note);
		return mv;
	}
	
	@PostMapping("/saveNote")
	public ModelAndView saveNote(String content, Integer patient) {
		nservice.saveNote(new Note(content,patient));
		return new ModelAndView("redirect:/patients/"+patient+"?noteAdded");	
	}

}