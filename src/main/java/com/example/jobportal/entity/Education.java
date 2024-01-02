package com.example.jobportal.entity;

import java.time.LocalDate;

import org.hibernate.annotations.GeneratorType;
import org.springframework.stereotype.Component;

import com.example.jobportal.enums.EducationType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Component
@Entity
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eduId;
	private EducationType eduType;
	private String degreeType;
	private String Stream;
	private String instituteName;
	private String location;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean gradStatus;
	private float percentageOrCGPA;
	
	@ManyToOne
	private Resume resumelist;
	
	public int getEduId() {
		return eduId;
	}
	public void setEduId(int eduId) {
		this.eduId = eduId;
	}
	public EducationType getEduType() {
		return eduType;
	}
	public void setEduType(EducationType eduType) {
		this.eduType = eduType;
	}
	public String getDegreeType() {
		return degreeType;
	}
	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}
	public String getStream() {
		return Stream;
	}
	public void setStream(String stream) {
		Stream = stream;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public boolean isGradStatus() {
		return gradStatus;
	}
	public void setGradStatus(boolean gradStatus) {
		this.gradStatus = gradStatus;
	}
	public float getPercentageOrCGPA() {
		return percentageOrCGPA;
	}
	public void setPercentageOrCGPA(float percentageOrCGPA) {
		this.percentageOrCGPA = percentageOrCGPA;
	}

	
	
}
