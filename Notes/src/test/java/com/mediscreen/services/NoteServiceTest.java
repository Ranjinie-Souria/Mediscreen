package com.mediscreen.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import com.mediscreen.model.Note;
import com.mediscreen.model.Patient;
import com.mediscreen.repository.NoteRepository;

@DataMongoTest
class NoteServiceTest {
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Mock
	private static PatientService patientService;
	@Autowired
	private NoteService noteService;
	
    @BeforeAll
    private static void setUp() throws Exception {
    	LocalDate todayLocalDate = LocalDate.now(ZoneId.systemDefault());
    	java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
    	Patient patient = new Patient(1,"familyName","firstName",sqlDate,"gender","address","phone");
    	PatientService myService = mock(PatientService.class, Mockito.RETURNS_DEEP_STUBS);
    	when(myService.getPatient(1)).thenReturn(patient);
    }

	@Test
	void createNote_should_saveNote() {
		int noteRepositorySize = noteRepository.findAll().size();
		noteService.createNote(new Note("Contenu",1));
		Assertions.assertEquals(noteRepositorySize+1, noteRepository.findAll().size());
	}
	
	@Test
	void deleteNote_should_deleteNote() {
		int noteRepositorySize = noteRepository.findAll().size();
		if(noteRepository.findAll().size()==0) {
			noteService.createNote(new Note("Contenu",1));
			noteRepositorySize = noteRepository.findAll().size();
		}
		String idNote = noteRepository.findAll().get(0).getId();
		noteService.deleteNoteById(idNote);
		Assertions.assertEquals(noteRepositorySize-1, noteRepository.findAll().size());
	}
	

}
