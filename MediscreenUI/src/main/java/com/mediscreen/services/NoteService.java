package com.mediscreen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mediscreen.model.Note;
import com.mediscreen.proxy.NoteProxy;


@Service
public class NoteService {
	
	@Autowired
	private NoteProxy proxy;
	
	public List<Note> getNotes(){
		return proxy.getNotes();
	}
	
	public List<Note> getNotesForPatient(int idPatient){
		return proxy.getNotesForPatient(idPatient);
	}
	
	public Note getNote(int id) {
		return proxy.getNote(id);
	}
	
	public Note saveNote(Note note) {
		Note savedNote;
        if(note.getId() == null) {
        	savedNote = proxy.createNote(note);
        } else {
        	savedNote = proxy.updateNote(note);
        }
        return savedNote;
	}
	
	public void deleteNote(String id) {
		proxy.deleteNote(id);
	}
	
	
	

}
