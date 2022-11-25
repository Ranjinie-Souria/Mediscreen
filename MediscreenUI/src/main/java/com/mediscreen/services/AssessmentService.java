package com.mediscreen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.model.Assessment;
import com.mediscreen.proxy.AssessmentProxy;

@Service
public class AssessmentService {
	
	@Autowired
	private AssessmentProxy proxy;
	
	public List<Assessment> getAssessments(String familyName) {
		return proxy.getAssessments(familyName);
	}
	
	public Assessment getAssessment(int id) {
		return proxy.getAssessment(id);
	}

}
