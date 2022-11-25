package com.mediscreen.model;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Patients")
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="patientId")
	private Integer patientId;
	
	@Column(name="familyName")
	@Size(min = 1)
	private String familyName;
	
	@Size(min = 1)
	@Column(name="firstName")
	private String firstName;
	
	/**
	 * Birthdate Format must be "yyyy-MM-dd"
	 */
	@Column(name="birthdate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthdate;
	
	@Size(min = 1)
	@Column(name="gender")
	private String gender;
	
	@Size(min = 1)
	@Column(name="address")
	private String address;
	
	@Column(name="phone")
	@Size(min = 1)
	private String phone;
	
	public Patient() {
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
	
	@Override
	public String toString() {
		return "Id=" + patientId + ", familyName=" + familyName + ", firstName=" + firstName
				+ ", birthdate=" + birthdate + ", gender=" + gender + ", address=" + address + ", phone=" + phone;
	}
	
	
	
	
}
