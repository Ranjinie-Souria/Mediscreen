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
import com.mediscreen.model.Note;
@Component
public class NoteProxy {
	
	private static final Logger log = LogManager.getLogger(NoteProxy.class);

	@Autowired
	private CustomProperties props;
	
	/**
	 * Get all Notes
	 * @return The notes
	 */
	public List<Note> getNotes(){
		String apiUrl = props.getApiNote();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Note>> response = restTemplate.exchange(
				apiUrl,
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<List<Note>>() {}
			);
		
		log.debug("Get Notes call " + response.getStatusCode().toString());

		return response.getBody();
	}
	
	/**
	 * Get all Notes for patient by the id
	 * @param id The id of the patient
	 * @return The notes
	 */
	public List<Note> getNotesForPatient(int id){
		String apiUrl = props.getApiNote();
		String getNoteUrl = apiUrl + "/patient/" + id;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Note>> response = restTemplate.exchange(
				getNoteUrl,
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<List<Note>>() {}
			);
		
		log.debug("Get Notes for patient call " + response.getStatusCode().toString());

		return response.getBody();
	}
	
	/**
	 * Get a Note by the id
	 * @param id The id of the note
	 * @return The note
	 */
	public Note getNote(int id) {
		String apiUrl = props.getApiNote();
		String getNoteUrl = apiUrl + "/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Note> response = restTemplate.exchange(
				getNoteUrl, 
				HttpMethod.GET, 
				null,
				Note.class
			);
		
		log.debug("Get Note : " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Add a new Note 
	 * @param note A new Note
	 * @return The Note full filled
	 */
	public Note createNote(Note note) {
		String apiUrl = props.getApiNote();
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Note> request = new HttpEntity<Note>(note);
		ResponseEntity<Note> response = restTemplate.exchange(
				apiUrl, 
				HttpMethod.POST, 
				request, 
				Note.class);
		
		log.debug("Create Note :  " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Update a Note
	 * @param note Existing note to update
	 */
	public Note updateNote(Note note) {
		String apiUrl = props.getApiNote();
		String updateNoteUrl = apiUrl + "/" + note.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Note> request = new HttpEntity<Note>(note);
		ResponseEntity<Note> response = restTemplate.exchange(
				updateNoteUrl, 
				HttpMethod.PUT, 
				request, 
				Note.class);
		
		log.debug("Update Note call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Delete a note
	 * @param id The note id to delete
	 */
	public void deleteNote(String id) {
		String apiUrl = props.getApiNote();
		String deleteNoteUrl = apiUrl + "/delete/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteNoteUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Note :  " + response.getStatusCode().toString());
	}

}