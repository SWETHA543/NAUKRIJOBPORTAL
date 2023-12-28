package com.example.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.entity.Skills;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.exceptionhandling.SkillNotFoundException;
import com.example.jobportal.requestdto.SkillRequestDto;
import com.example.jobportal.responsedto.SkillResponseDto;
import com.example.jobportal.service.SkillService;
import com.example.jobportal.utility.ResponseStructure;



@RestController
public class SkillController {
	

	@Autowired
	private SkillService skillService;
	
	
	@PostMapping("resumes/{resumId}/skills")  
	public ResponseEntity<ResponseStructure<String>> insertSkill(@PathVariable int resumId,@RequestBody SkillRequestDto reqSkill) 
			throws ResumeNotFoundException  
	{
		
		 return skillService.insertSkill(reqSkill,resumId);
		
	}

	
	@PutMapping("resumes/{resumId}/skills")  
	public ResponseEntity<ResponseStructure<String>> updateSkill(@PathVariable int resumId,@RequestBody SkillRequestDto reqSkill) 
			throws ResumeNotFoundException  
	{
		
		 return skillService.updateSkill(reqSkill,resumId);
		
	}
	@DeleteMapping("resumes/{resumId}/skills/{skill}")  
	public ResponseEntity<ResponseStructure<String>> deleteSkillInResume(@PathVariable int resumId,@PathVariable String skill) throws SkillNotFoundException, ResumeNotFoundException 
			 
	{     
		 
		 return skillService.deleteSkillInResume(resumId,skill);
		
	}
	
	@GetMapping("/skills/{skill}")  
	public ResponseEntity<ResponseStructure<SkillResponseDto>> findSkillByName(@PathVariable String skill) throws SkillNotFoundException 
			 
	{     
		 
		 return skillService.findSkillByName(skill);
		
	}
	
	@GetMapping("resumes/{resumeId}/skills")  
	public ResponseEntity<ResponseStructure<List<SkillResponseDto>>> findSkillByResumeId(@PathVariable int resumeId) throws SkillNotFoundException 
			 
	{     
		 
		 return skillService.findSkillByResumeId(resumeId);
		
	}
	

}
