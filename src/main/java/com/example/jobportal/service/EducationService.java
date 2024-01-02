package com.example.jobportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Education;
import com.example.jobportal.entity.Resume;
import com.example.jobportal.repository.EducationRepository;
import com.example.jobportal.requestdto.EducationRequestDto;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class EducationService {
	@Autowired
	private EducationRepository edurepository;

	public ResponseEntity<ResponseStructure<String>> insertEducationService(EducationRequestDto dto, int resumeId) {
		Optional<Education> findById = edurepository.findById(resumeId);
		
		
		if(findById.isPresent())
		{
			
			       Education education = findById.get();
			               Education convertToEducation = convertToEducation(dto,  new Education());
			                    Resume res=new Resume();
			                    List<Education> eduList = res.getEduList();
			               
		}
		
		
		
		
	}

	private Education convertToEducation(EducationRequestDto dto, Education education) {
		education.setDegreeType(dto.getDegreeType());
		education.setInstituteName(dto.getInstituteName());
		education.setLocation(dto.getLocation());
		education.setStartDate(dto.getStartDate());
		education.setEndDate(dto.getEndDate());
		education.setGradStatus(dto.isGradStatus());
		education.setPercentageOrCGPA(dto.getPercentageOrCGPA());
		education.setStream(dto.getStream());
		return education;
	}

	

}
