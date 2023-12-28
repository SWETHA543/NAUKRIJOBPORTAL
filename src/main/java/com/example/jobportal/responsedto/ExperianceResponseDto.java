package com.example.jobportal.responsedto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class ExperianceResponseDto {
	
	private int experianceId;
	private String companyName;
	private boolean isPresent;
	private LocalDate startingDate;
	private LocalDate endDate;
	private String role;
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

}
