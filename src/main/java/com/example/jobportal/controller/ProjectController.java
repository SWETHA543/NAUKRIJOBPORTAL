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

import com.example.jobportal.requestdto.ProjectRequestDto;
import com.example.jobportal.responsedto.ProjectResponseDto;
import com.example.jobportal.service.ProjectService;
import com.example.jobportal.utility.ResponseStructure;

@RestController
public class ProjectController {
	
	@Autowired
	ProjectService projectservice;
	
	
	@PostMapping("/resumes/{resumeid}/projects")
	
	public ResponseEntity<ResponseStructure<String>> insertProject(@RequestBody ProjectRequestDto projects, @PathVariable int resumeid)
	{
		ResponseEntity<ResponseStructure<String>> allser=projectservice.insertProjectService(projects, resumeid);
		return allser;
	}
	@GetMapping("/projects/{projectid}/projects")
	public ResponseEntity<ResponseStructure<ProjectResponseDto>> findById(@PathVariable int projectid)
	{
		ResponseEntity<ResponseStructure<ProjectResponseDto>> allserv=projectservice.findByIdService(projectid);
		return allserv;
	}
	
	@PutMapping("/projects/{projectid}/projects")
	public ResponseEntity<ResponseStructure<String>> UpdateProject(@RequestBody ProjectRequestDto dto,@PathVariable int  projectid)
	{
		ResponseEntity<ResponseStructure<String>> allser=projectservice.updateProjectService(dto, projectid);
		
		return allser;
	}
	@DeleteMapping("/projects/{projectid}")
	public ResponseEntity<ResponseStructure<String>> deleteProjectController(@PathVariable int projectid)
	{
		ResponseEntity<ResponseStructure<String>> allsrev=projectservice.deleteProjectService(projectid);
		return allsrev;
	}

}
