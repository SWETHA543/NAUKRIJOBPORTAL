package com.example.jobportal.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Component
@Entity
public class Resume {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int resumeId;
	private String objective;
	
	@OneToOne
	private User user;
	@OneToMany(mappedBy = "resume")
	private List<Experiance> li;
	
	@OneToMany
	private List<Skills> liskills;
	
	
	
	public List<Skills> getLiskills() {
		return liskills;
	}
	public void setLiskills(List<Skills> liskills) {
		this.liskills = liskills;
	}
	public List<Experiance> getLi() {
		return li;
	}
	public void setLi(List<Experiance> li) {
		this.li = li;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getResumeId() {
		return resumeId;
	}
	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	
	

}
