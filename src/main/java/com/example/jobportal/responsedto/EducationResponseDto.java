package com.example.jobportal.responsedto;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class EducationResponseDto {
	
	private int eduId;
	private HashMap<String,String > options;
	private String degreeType;
	private String Stream;
	private String instituteName;
	private String location;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean gradStatus;
	private float percentageOrCGPA;
	public int getEduId() {
		return eduId;
	}
	public void setEduId(int eduId) {
		this.eduId = eduId;
	}
	public HashMap<String, String> getOptions() {
		return options;
	}
	public void setOptions(HashMap<String, String> options) {
		this.options = options;
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
