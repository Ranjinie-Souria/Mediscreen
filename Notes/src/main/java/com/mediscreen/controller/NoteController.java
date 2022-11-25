package com.mediscreen.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.model.Note;
import com.mediscreen.services.NoteService;


@RestController
public class NoteController {
	
	@Autowired
	private NoteService noteService;

	@GetMapping("/notes")
    public List<Note> getNotes()
    {
    	return noteService.getNotes();
    }
	
	@GetMapping("/notes/patient/{id}")
    public List<Note> getNotesForPatient(@PathVariable int id)
    {
    	return noteService.getNotesByPatient(id);
    }
	
	@PostMapping("/notes")
    public  ResponseEntity<Note> createNote(@RequestBody Note note)
    {
		Note noteAdded = noteService.createNote(note);
    	if (Objects.isNull(noteAdded)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
    }
	
	@DeleteMapping (value = "/notes/delete/{id}")
    public void deleteNote(@PathVariable String id) {
	 noteService.deleteNoteById(id);
    }

}
