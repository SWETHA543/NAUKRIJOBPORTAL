package com.example.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.exceptionhandling.CompanyNotFoundException;
import com.example.jobportal.requestdto.JobRequestDto;
import com.example.jobportal.responsedto.JobResponseDto;
import com.example.jobportal.service.JobService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;



@RestController
public class JobController {
	@Autowired
	JobService jobService;
	
	
	
	@PostMapping("/companies/{companyId}/jobs")  
	public ResponseEntity<ResponseStructure<String>> inserJob(@RequestBody  JobRequestDto jobrequestdto,@PathVariable int companyId) 
			throws CompanyNotFoundException
	{
		
		 return jobService.insertJOb(jobrequestdto,companyId);
		
	}
	@GetMapping("jobs/{id}")
	public ResponseEntity<ResponseStructure<JobResponseDto>> findById(@PathVariable  int id)
	{
		
		ResponseEntity<ResponseStructure<JobResponseDto>> allser=jobService.findByIdService(id);
		return allser;
	}
	@PutMapping("jobs/{id}/jobs")
	public ResponseEntity<ResponseStructure<JobResponseDto>> updateJob(@RequestBody  JobRequestDto li,@PathVariable int id)
	{
		ResponseEntity<ResponseStructure<JobResponseDto>> allup=jobService.updateJobService(li,id);
		return allup;
	}
	@DeleteMapping("/jobs/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteJob(@PathVariable  int id)
	{
		ResponseEntity<ResponseStructure<String>> deleteJob=jobService.deleteJobService(id);
		return deleteJob;
		
	}

	

}
