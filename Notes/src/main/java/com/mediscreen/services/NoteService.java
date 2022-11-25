package com.mediscreen.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.model.Note;
import com.mediscreen.repository.NoteRepository;

@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepository;
	private final Logger logger = LoggerFactory.getLogger(NoteService.class);
	
	public List<Note> getNotes() {
		return noteRepository.findAll();
	}
	
	public Note getNote(String id) {
		Optional<Note> note = noteRepository.findById(id);
		if (note.isPresent()) {
            return note.get();
	    }
		else {
	            logger.info("Note not found");
	            return null;
	   }
	}
	
	public List<Note> getNotesByPatient(int id) {
		return noteRepository.findByPatientOrderByDateDesc(id);
	}
	
	public Note createNote(Note note) {
		if(note.getPatient() != null) {
			if(note.getContent() != null) {
				if(note.getDate() == null) {
					LocalDateTime todayLocalDate = LocalDateTime.now(ZoneId.systemDefault());
					java.sql.Timestamp sqlDate = java.sql.Timestamp.valueOf(todayLocalDate);
					note.setDate(sqlDate);
				}
				noteRepository.save(note);
			}
		}
		return note;
	}

	public void deleteNoteById(String id) {
		noteRepository.deleteById(id);
	}
	

}
