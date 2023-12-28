package com.example.jobportal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Resume;
import com.example.jobportal.entity.User;
import com.example.jobportal.exceptionhandling.ResumeNotFoundByThisId;
import com.example.jobportal.exceptionhandling.UserNotFoundException;
import com.example.jobportal.exceptionhandling.UserNotFounfByThisId;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.ResumeRequestDto;
import com.example.jobportal.responsedto.ResumeResponseDto;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class ResumeService {
	
	@Autowired
	ResumeRepository resumerepository;
	@Autowired
	UserRepository userRepository;
	
	
	private Resume convertToResume(ResumeRequestDto resumerequestdto) {
		ResumeRequestDto dto=new ResumeRequestDto();
		Resume resume=new Resume();
		resume.setObjective(dto.getObjective());;
		
		return resume;
		
	}

	public ResponseEntity<ResponseStructure<String>> addResumeService(ResumeRequestDto resumerequestdto, int userid) throws UserNotFoundException {
		
		    Optional<User> findById = userRepository.findById(userid);
		    
		    if(findById.isPresent())
		    {
		    	              Resume convertToResume = convertToResume(resumerequestdto)  ;   
		    	              
		    	            resumerepository.save(convertToResume) ;
		    	            
		    	            ResponseStructure<String> rs=new ResponseStructure<String>();
		    	            rs.setStatusCode(HttpStatus.CREATED.value());
		    	            rs.setMessage("details added successfully");
		    	            rs.setData("resume data added successfully");
		    	            
		    	            
		    	            return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.CREATED);
		    	
		    	
		    }
		    else  throw  new  UserNotFoundException("user not found by 5this Id");
		    	
		
		
	}

	public ResponseEntity<ResponseStructure<ResumeResponseDto>> findByIdService(int resumeid) {
		
				Optional<Resume> findById = resumerepository.findById(resumeid);
				if(findById.isPresent())
				{
					   Resume resume = findById.get();
					    ResumeResponseDto convertToResponseDto = convertToResponseDto(resume);
					    ResponseStructure<ResumeResponseDto> rs=new ResponseStructure<ResumeResponseDto>();
					    rs.setStatusCode(HttpStatus.FOUND.value());
					    rs.setMessage("data fetched successfully by given id");
					    rs.setData(convertToResponseDto);
					    
					    return new ResponseEntity<ResponseStructure<ResumeResponseDto>>(rs,HttpStatus.FOUND);
					    
				}
				else throw new UserNotFounfByThisId("user not found by this id please try again later");
	}

	private ResumeResponseDto convertToResponseDto(Resume resume) {
		ResumeResponseDto res1=new  ResumeResponseDto();
		res1.setResumeId(resume.getResumeId());
		return res1;
		
	}

	public ResponseEntity<ResponseStructure<String>> deleteByIdService(int id) {
		          Optional<Resume> findById = resumerepository.findById(id);
		          
		          if(findById.isPresent())
		          {
		        	  resumerepository.deleteById(id);
		        	  ResponseStructure<String> rs=new ResponseStructure<>();
		        	  rs.setStatusCode(HttpStatus.FOUND.value());
		        	  rs.setMessage("Resume is deleted successfully");
		        	  rs.setData("deleted data by this Id");
		        	  
		        	  return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.FOUND);
		          }
		          else throw new ResumeNotFoundByThisId("   Resume not found by this Id");
		        	  
		          
	}

	

}
