package com.example.jobportal.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Component
@Entity
public class Skills {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int skillId;
	private String skillName;
	
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getSkill() {
		return skillName;
	}
	public void setSkill(String skill) {
		this.skillName = skill;
	}
	
	

}
