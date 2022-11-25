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
	
	public List<Note> getNotesForPatient(int idPatient){
		return proxy.getNotesForPatient(idPatient);
	}
}
