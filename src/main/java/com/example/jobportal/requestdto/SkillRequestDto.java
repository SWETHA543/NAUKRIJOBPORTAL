package com.example.jobportal.requestdto;

import org.springframework.stereotype.Component;

@Component
public class SkillRequestDto {
	
	private String[] skill;

	public String[] getSkill() {
		return skill;
	}

	public void setSkill(String[] skill) {
		this.skill = skill;
	}

	

}
