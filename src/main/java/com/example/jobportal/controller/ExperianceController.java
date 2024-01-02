package com.example.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.WorkExperienceNotFoundException;
import com.example.jobportal.exceptionhandling.ExperianceNotFoundException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.requestdto.ExperianceRequestDto;
import com.example.jobportal.responsedto.ExperianceResponseDto;
import com.example.jobportal.service.ExperianceService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class ExperianceController {
	@Autowired
	ExperianceService workExperienceService;
	

	@PostMapping("/resumes/{resumId}/works")  
	public ResponseEntity<ResponseStructure<String>> insertWork(@PathVariable int resumId,@RequestBody  ExperianceRequestDto reqWork) throws ResumeNotFoundException 
		 
	{
		
		 return workExperienceService.insertWork(reqWork,resumId);
		
	}

	@PutMapping("/works/{workId}")  
	public ResponseEntity<ResponseStructure<String>> updateWork(@PathVariable int workId ,@RequestBody  ExperianceRequestDto reqWork) throws WorkExperienceNotFoundException 
		 
	{
		
		 return workExperienceService.updateWork(reqWork, workId);
		
	}
	
	@GetMapping("/works/{workId}")  
	public ResponseEntity<ResponseStructure<ExperianceResponseDto>> findWorkByWorkId(@PathVariable int workId ) throws WorkExperienceNotFoundException 
		 
	{
		
		 return workExperienceService.findWorkByWorkId(workId);
		
	}
	
	@GetMapping("/resumes/{resumeId}/works")  
	public ResponseEntity<ResponseStructure<List<ExperianceRequestDto>>> findWorkByResumeId(@PathVariable int resumeId ) throws ExperianceNotFoundException, ResumeNotFoundException 
		 
	{
		
		 return workExperienceService.findWorkByResumeId(resumeId);
		
	}

	

}
