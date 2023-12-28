package com.example.jobportal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Project;
import com.example.jobportal.entity.Resume;
import com.example.jobportal.exceptionhandling.ProjectNotFoundByIdException;
import com.example.jobportal.exceptionhandling.ProjectNotFoundException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.repository.ProjectRepository;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.requestdto.ProjectRequestDto;
import com.example.jobportal.responsedto.ProjectResponseDto;
import com.example.jobportal.utility.ResponseStructure;



@Service
public class ProjectService {


	@Autowired
	ProjectRepository projectrepo;
	@Autowired
	ResumeRepository resumerepo;


	public ResponseEntity<ResponseStructure<String>> insertProjectService(ProjectRequestDto projReq,int resumeid) {

		Optional<Resume> optRes = resumerepo.findById(resumeid);

		if(optRes.isPresent())
		{
			Resume resume = optRes.get();
			Project project= convertProjecrRequestToProject(projReq, new Project());

			project.setResume(resume);
			projectrepo.save(project);

			ResponseStructure<String> rs=new ResponseStructure<String>();
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setMessage("data added successfully");
			rs.setMessage("project is added successfully for givrn resumeId");
			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.CREATED);

		}
		else {
			throw  new  ResumeNotFoundException("Resume is not present to add the project ... plz check it once");
		}
	}


	private Project convertProjecrRequestToProject(ProjectRequestDto projects, Project pro) {


		pro.setProjectName(projects.getProjectName());
		pro.setSourceCode(projects.getSourceCode());
		pro.setTechStack(projects.getTechStack());
		pro.setDescription(projects.getDescription());
		pro.setWebsite(projects.getWebsite());

		return pro;

	}




	public ResponseEntity<ResponseStructure<ProjectResponseDto>> findByIdService(int projectid) {

		Optional<Project> optPro = projectrepo.findById(projectid);


		if(optPro.isPresent())
		{    Project project = optPro.get();

		ProjectResponseDto dto = convertToProjectResponseDto(project);

		ResponseStructure<ProjectResponseDto> rs=new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.FOUND.value());
		rs.setMessage("data added successfully");
		rs.setMessage("project is added successfully for givrn resumeId");
		rs.setData(dto);
		return new ResponseEntity<ResponseStructure<ProjectResponseDto>>(rs, HttpStatus.FOUND);

		}
		else {
			throw  new  ProjectNotFoundException("Project is not present with given Id ... plz check it once");
		}


	}



	private ProjectResponseDto  convertToProjectResponseDto(Project projects) {
		ProjectResponseDto dto=new ProjectResponseDto();
		dto.setProjectid(projects.getProjectId());
		dto.setProjectName(projects.getProjectName());
		dto.setSourceCode(projects.getSourceCode());
		dto.setTechStack(projects.getTechStack());
		dto.setDescription(projects.getDescription());
		dto.setWebsite(projects.getWebsite());

		return dto;


	}


	public ResponseEntity<ResponseStructure<String>> updateProjectService(ProjectRequestDto dto, int projectid) {

		Optional<Project> optId = projectrepo.findById(projectid);
		if(optId.isPresent())
		{
			Project existing = optId.get();
			Project updated = convertToProject(dto,existing);


			projectrepo.save(updated);

			ResponseStructure<String> rs=new ResponseStructure<String>();
			rs.setStatusCode(HttpStatus.FOUND.value());
			rs.setMessage("project updated successfully");
			rs.setData("project updated successfully by given details");

			return new   ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.FOUND);	                  
		}
		else {
			throw new ProjectNotFoundByIdException("project is not present by this Id to update");
		}

	}


	private Project convertToProject(ProjectRequestDto dto, Project pro) {

		pro.setProjectName(dto.getProjectName());
		pro.setSourceCode(dto.getSourceCode());
		pro.setTechStack(dto.getTechStack());
		pro.setDescription(dto.getDescription());
		pro.setWebsite(dto.getWebsite());

		return pro;


	}


	public ResponseEntity<ResponseStructure<String>> deleteProjectService(int projectid) {
		
		Optional<Project> optId = projectrepo.findById(projectid);
		if(optId.isPresent())
		{
			projectrepo.deleteById(projectid);
			
			ResponseStructure<String> rs=new ResponseStructure<String>();
			
			rs.setStatusCode(HttpStatus.FOUND.value());
			rs.setMessage("project deleted successfully");
			rs.setData("project deleted successfully ");
			
			return new  ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.FOUND);
			
		}
		else
		{
			throw new ProjectNotFoundByIdException("project is not found by this id to complete the delete operation");
		}


	}

}
