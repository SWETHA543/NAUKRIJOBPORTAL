package com.example.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.exceptionhandling.UserNotFoundException;
import com.example.jobportal.requestdto.ResumeRequestDto;
import com.example.jobportal.responsedto.ResumeResponseDto;
import com.example.jobportal.service.ResumeService;
import com.example.jobportal.utility.ResponseStructure;

@RestController
public class ResumeController {
	@Autowired
	ResumeService resumeservice;
	
	@PostMapping("/users/{userid}/resumes")
	public ResponseEntity<ResponseStructure<String>> addResume(@RequestBody ResumeRequestDto resumerequestdto, @PathVariable int userid) throws UserNotFoundException
	{
		ResponseEntity<ResponseStructure<String>> allserv=resumeservice.addResumeService(resumerequestdto, userid);
		return allserv;
		
	}
	@GetMapping("/resumes/{resumeid}")
	public ResponseEntity<ResponseStructure<ResumeResponseDto>> findById(@PathVariable int resumeid)
	{
		ResponseEntity<ResponseStructure<ResumeResponseDto>> allser=resumeservice.findByIdService(resumeid);
		return allser;
	}
	@DeleteMapping("/resumes/{id}")
	public ResponseEntity<ResponseStructure<String>>  deleteResumeById(@PathVariable int id)
	{
		ResponseEntity<ResponseStructure<String>> allserv=resumeservice.deleteByIdService(id);
		return allserv  ;
	}


}
