package com.example.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.requestdto.EducationRequestDto;
import com.example.jobportal.service.EducationService;
import com.example.jobportal.utility.ResponseStructure;

@RestController
public class EducationController {
	
	@Autowired 
	private EducationService eduservice;
	
	@PostMapping("/resumes/{resumeId}/educations")
	public ResponseEntity<ResponseStructure<String>> insertEducation(@RequestBody EducationRequestDto dto, @PathVariable int resumeId)
{
		ResponseEntity<ResponseStructure<String>> allser=eduservice.insertEducationService(dto, resumeId);
		return allser;
}
	
	

}
