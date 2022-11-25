package com.mediscreen.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.model.Assessment;
import com.mediscreen.model.Note;
import com.mediscreen.model.Patient;

@Service
public class AssessmentService {
	
	@Autowired
	private NoteService nService;
	
	@Autowired
	private PatientService pService;
	
	public static List<String> triggers() {
		List<String> noteTriggers = new ArrayList<String>();
		noteTriggers.add("A1C");
		noteTriggers.add("Microalbumine");
		noteTriggers.add("Taille");
		noteTriggers.add("Poids");
		noteTriggers.add("Fumeur");
		noteTriggers.add("Anormal");
		noteTriggers.add("Cholestérol");
		noteTriggers.add("Vertige");
		noteTriggers.add("Rechute");
		noteTriggers.add("Réaction");
		noteTriggers.add("Anticorps");
		return noteTriggers;
	}
	
	public Assessment getAssessmentForPatientId(Integer patientId) {
		int patientAge = calculateAgeWithPatientId(patientId);
		String result = calculateAssessmentResult(patientId);
		return new Assessment(patientId,patientAge,result);

	}
	
	public int calculateAgeWithPatientId(Integer patientId) {
		
		Patient p = pService.getPatient(patientId);
		LocalDate birth = p.getBirthdate().toLocalDate();
		LocalDate currentdate = LocalDate.now();
		Period period = Period.between(birth, currentdate);
		return period.getYears();
			
	}
	

	
	public String calculateAssessmentResult(int patientId) {
		String result = "None";
		List<Note> notes = nService.getNotesForPatient(patientId);
		int age = this.calculateAgeWithPatientId(patientId);
		String gender = pService.getPatient(patientId).getGender();
		int triggerCount = 0;
		
		for(Note note : notes) {
			
			String content = note.getContent();
			String[] words = content.split("\\s+");
			for (int i = 0; i < words.length; i++) {
			    words[i] = words[i].replaceAll("[^\\w]", "");
			}
			
			for(String trigger : AssessmentService.triggers()) {
				
				for(String word : words) {
					if(word.equalsIgnoreCase(trigger)) {
						triggerCount = triggerCount + 1;
					}
				}
			}
			
		}
		
		if(triggerCount == 0) {
			return "None";
		}
		else {
			if(age > 30 ) {
				if(triggerCount == 2) {
					result = "Borderline";
				}
				if(triggerCount > 2) {
					if(triggerCount >= 6 && triggerCount < 8) {
						result = "In Danger";
					}
					if(triggerCount >= 8) {
						result = "Early Onset";
					}
				}

			}
			else {
				if(gender.toLowerCase() == "f") {
					if(triggerCount >= 4 && triggerCount < 7) {
						result = "In Danger";
					}
					else if(triggerCount >= 7 ) {
						result = "Early onset";
					}
				}
				if(gender.toLowerCase() == "m") {
					if(triggerCount >= 3 && triggerCount < 5) {
						result = "In Danger";
					}
					else if(triggerCount >= 5 ) {
						result = "Early onset";
					}
				}
			}
		}
		return result;
	}

}
