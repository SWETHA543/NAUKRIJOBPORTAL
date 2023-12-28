package com.example.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.requestdto.ExperianceRequestDto;
import com.example.jobportal.service.ExperianceService;
import com.example.jobportal.utility.ResponseStructure;

@RestController
public class ExperianceController {
	@Autowired
	private ExperianceService expservice;
	
	@PostMapping("/resumes/{resumeid}/experiances")
	public ResponseEntity<ResponseStructure<String>> insertExperiance(@RequestBody ExperianceRequestDto dto, @PathVariable int resumeid )
	{
		ResponseEntity<ResponseStructure<String>> allserv=expservice.insertExperianceService(dto, resumeid);
		return allserv;
	}

}
