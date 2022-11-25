package com.mediscreen.model;

import java.sql.Date;

public class Patient {
	
	private Integer patientId;
	private String familyName;
	private String firstName;
	private Date birthdate;
	private String gender;
	private String address;
	private String phone;
	
	public Patient() {
	}
	
	public Patient(Integer patientId, String familyName, String firstName, Date birthdate, String gender,
			String address, String phone) {
		this.patientId = patientId;
		this.familyName = familyName;
		this.firstName = firstName;
		this.birthdate = birthdate;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
	}
	
	public Patient(String familyName, String firstName, Date birthdate, String gender, String address, String phone) {
		this.familyName = familyName;
		this.firstName = firstName;
		this.birthdate = birthdate;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
	}

	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
