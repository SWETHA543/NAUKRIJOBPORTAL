package com.example.jobportal.responsedto;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class SkillResponseDto {
	
	private int skillId;
	private String skill;
	private HashMap<String,String > options;
	
	
	public HashMap<String, String> getOptions() {
		return options;
	}
	public void setOptions(HashMap<String, String> options) {
		this.options = options;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}

}
