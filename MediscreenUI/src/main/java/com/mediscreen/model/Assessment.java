package com.mediscreen.model;

public class Assessment {
	
	private Integer patientId;
	private int patientAge;
	private String result;
	
	public Assessment(Integer patientId, int patientAge, String result) {
		this.patientId = patientId;
		this.patientAge = patientAge;
		this.result = result;
	}
	
	public Assessment(Integer patientId) {
		this.patientId = patientId;		
	}
	
	public Assessment() {
	}
	
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
}