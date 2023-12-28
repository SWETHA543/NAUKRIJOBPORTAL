package com.example.jobportal.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Component
@Entity
public class Experiance {
	
	@Id
	
	private int experianceId;
	private String companyName;
	private boolean isPresent;
	private LocalDate startingDate;
	private LocalDate endDate;
	private float yearOfExperiance;
	public float getYearOfExperiance() {
		return yearOfExperiance;
	}

	public void setYearOfExperiance(float yearOfExperiance) {
		this.yearOfExperiance = yearOfExperiance;
	}

	private String role;
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	private Resume resume;

	public int getExperianceId() {
		return experianceId;
	}

	public void setExperianceId(int experianceId) {
		this.experianceId = experianceId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public LocalDate getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(LocalDate startingDate) {
		this.startingDate = startingDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}
	

}
