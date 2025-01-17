package com.example.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.entity.SocialProfile;
import com.example.jobportal.enums.SocialType;
import com.example.jobportal.requestdto.SocialProfileRequestDto;
import com.example.jobportal.service.SocialProfileService;
import com.example.jobportal.utility.ResponseStructure;

@RestController
public class SocialProfileController {

	@Autowired
	private SocialProfileService service;
	 
	
	@PostMapping("resumes/{resumeId}/socialtype/{socialtype}/socialprofiles")
	public ResponseEntity<ResponseStructure<String>> insertSocialProfile(@RequestBody SocialProfileRequestDto dto, @PathVariable int resumeId, @PathVariable SocialType socialtype) throws IllegalAccessException
	{
		ResponseEntity<ResponseStructure<String>> allserv=service.insertSocialProfileService(dto, resumeId,socialtype);
		return allserv;
	}
	@GetMapping("/resumes/s")
	


}
