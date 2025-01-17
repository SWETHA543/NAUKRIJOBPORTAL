package com.example.jobportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Resume;
import com.example.jobportal.entity.SocialProfile;
import com.example.jobportal.enums.SocialType;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.SocialProfileRepository;
import com.example.jobportal.requestdto.SocialProfileRequestDto;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class SocialProfileService {
	
	@Autowired
	private SocialProfileRepository repo;
	
	@Autowired
	private ResumeRepository resrepo;

	public ResponseEntity<ResponseStructure<String>> insertSocialProfileService(SocialProfileRequestDto dto,int resumeId,  SocialType socialtype) throws IllegalAccessException {
		 			Optional<Resume> optres = resrepo.findById(resumeId);
		 			
		 			if(optres.isPresent())
		 			{
		 				Resume resume = optres.get();
		 				       
		 				       
		 				       List<SocialProfile> socialport = resume.getSocialport();
		 				       
		 				      if(!socialport.isEmpty())
		 				      {
		 				    	 for(SocialProfile port:socialport)
			 				       {
			 				    	   if(port.getSocialtype()==socialtype && port.getSocialtype()==SocialType.Twitter )
			 				    		   throw new IllegalAccessException("Twitter already present u cant enter , you can update it");
			 				    	   else if (port.getSocialtype()==socialtype && port.getSocialtype()==SocialType.Git_hub) 
			 				    		   throw new IllegalAccessException("GitHub already present u cant enter , you can update it");
			 				    	   
			 				    	  else if (port.getSocialtype()==socialtype && port.getSocialtype()==SocialType.Gmail) 
			 				    		   throw new IllegalAccessException("Gmail already present u cant enter , you can update it");
			 				    	   
			 				    	 else if (port.getSocialtype()==socialtype && port.getSocialtype()==SocialType.Linked_in) 
			 				    		   throw new IllegalAccessException("linked in already present u cant enter , you can update it");
			 				    	else if (port.getSocialtype()==socialtype && port.getSocialtype()==SocialType.PortFolio) 
			 				    		   throw new IllegalAccessException("PortFolio already present u cant enter , you can update it");



										
									
			 				       }
			 				       
		 				      }
		 				       
		 				      SocialProfile convertToSocial = convertToSocial(dto, new SocialProfile());
		 				       convertToSocial.setRes(resume);
		 				      convertToSocial.setSocialtype(socialtype);
		 				      
		 				      repo.save(convertToSocial);
		 				       
		 				      ResponseStructure<String> rs=new ResponseStructure<String>();
		 				      rs.setStatusCode(HttpStatus.CREATED.value());
		 				      rs.setMessage("data addee successfully");
		 				      rs.setData("socialprofile Added Successfully");
		 				       
		 				       return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.CREATED);
		 				       
		 				       
		 			}
		 			else throw new ResumeNotFoundException("resume not found to add your social profiles");
		
		
			
		
	}

	private SocialProfile convertToSocial(SocialProfileRequestDto dto, SocialProfile sp) {
		
		sp.setUrl(dto.getUrl());
		return sp;
		
	}

}
