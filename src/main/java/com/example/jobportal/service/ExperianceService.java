package com.example.jobportal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Experiance;
import com.example.jobportal.entity.Resume;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.repository.ExperianceRepository;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.requestdto.ExperianceRequestDto;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class ExperianceService {
	@Autowired
	
	private ExperianceRepository exprepo;
	@Autowired
	private ResumeRepository resrepo;

	public ResponseEntity<ResponseStructure<String>> insertExperianceService(ExperianceRequestDto dto, int resumeid) {
		Optional<Resume> optId = resrepo.findById(resumeid);
		
		if(optId.isPresent())
		{
			     Experiance expId = convertToExperiance(dto, new Experiance());
			     
			     
			     exprepo.save(expId);
			     ResponseStructure<String> rs=new ResponseStructure<String>();
			     rs.setStatusCode(HttpStatus.CREATED.value());
			     rs.setMessage("experiance added successfully");
			     rs.setData("experiance added successfully in resume");
			     
			     return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.CREATED);
			     
		}
		else {
			 throw new ResumeNotFoundException("resume not sound by this Id");
		}
		
		
		
	}

	private Experiance convertToExperiance(ExperianceRequestDto dto, Experiance exp) {
		
		exp.setCompanyName(dto.getCompanyName());
		exp.setStartingDate(dto.getStartingDate());
		exp.setEndDate(dto.getEndDate());
		exp.setRole(dto.getRole());
		exp.setDescription(dto.getDescription());
		
		return exp;
		
		
	}
	

}
