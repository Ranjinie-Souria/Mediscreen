package com.mediscreen.repository;

import com.mediscreen.model.Patient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Integer>{

	List<Patient> findByAddress(String address);
	List<Patient> findByFamilyName(String familyName);
	
}
