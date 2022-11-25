package com.mediscreen.model;

import java.util.Date;

public class Note {
	
	private String id;
	private Date date;
	private String content;
	private Integer patient;
	
	public Note() {
	}
	
	public Note(String id, Date date, String content, Integer patient) {
		this.id = id;
		this.date = date;
		this.content = content;
		this.patient = patient;
	}
	
	public Note(Date date, String content, Integer patient) {
		this.date = date;
		this.content = content;
		this.patient = patient;
	}
	
	public Note(String content, Integer patient) {
		this.content = content;
		this.patient = patient;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getPatient() {
		return patient;
	}
	public void setPatient(Integer patient) {
		this.patient = patient;
	}
	
	
	

}
