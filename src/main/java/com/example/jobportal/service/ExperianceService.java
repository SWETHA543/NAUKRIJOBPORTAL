package com.example.jobportal.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Experiance;
import com.example.jobportal.entity.Resume;
import com.example.jobportal.exceptionhandling.ExperianceNotFoundException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.repository.ExperianceRepository;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.requestdto.ExperianceRequestDto;
import com.example.jobportal.responsedto.ExperianceResponseDto;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class ExperianceService {
	 @Autowired
		private ResumeRepository resumRepo;
	    @Autowired
	    private ExperianceRepository workRepo;
		private ExperianceResponseDto convertToWorkDto;
	    
	    
	    
	    
	    private Experiance convertToWork(ExperianceRequestDto dto, Experiance work)
	    {
	    	
	    	work.setDescription(dto.getDescription());
	    	work.setEndDate(dto.getEndDate());
	    	work.setJobRole(dto.getJobRole());
	    	work.setJobStatus(dto.getJobStatus());
	    	work.setOrganisation(dto.getOrganisation());
	    	work.setStartDate(dto.getStartDate());
	    return work;
	    	
	  	
	    }
	    
	    private ExperianceResponseDto convertToWorkDto(Experiance work)
	    {
	    	ExperianceResponseDto dto = new ExperianceResponseDto();
	    	dto.setDescription(work.getDescription());
	    	dto.setEndDate(work.getEndDate());
	    	dto.setJobRole(work.getJobRole());
	    	dto.setJobStatus(work.getJobStatus());
	    	dto.setOrganisation(work.getOrganisation());
	    	dto.setStartDate(work.getStartDate());
	    	dto.setYearsOfExperience(work.getYearsOfExperience());
	    	dto.setExpId(work.getExpId());
	    
	    return dto;
	   	
	    }
	    
		public ResponseEntity<ResponseStructure<String>> insertWork(ExperianceRequestDto reqWork, int resumId) throws ResumeNotFoundException {

	          Optional<Resume> optResume = resumRepo.findById(resumId);		
			
			if (optResume.isPresent()) {
				
				Resume resume = optResume.get();

				          Experiance work = convertToWork(reqWork, new Experiance());
				          
				          work.setAssociatedResume(resume);
				         if(work.getJobStatus()==true) {work.setEndDate(null);}
				          
				          Period per;
				          if( work.getEndDate()!=null)
				          {
				        	  
				        	  per = Period.between(work.getStartDate(),  work.getEndDate());
				        	   work.setYearsOfExperience(per+"");
				        	       
				          }
				         
				          else {
				        	   per = Period.between(work.getStartDate(),LocalDate.now());
			        	       work.setYearsOfExperience(per+"");
			        	      
				          }
				
				          workRepo.save(work);
				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" work data saved successfully");
				respStruc.setData(" WORKEXPERIENCE ADDED  SUCCESSFULLY");
	 
				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
			}

			else throw new ResumeNotFoundException(" Resume with given Id not Present");


		}


		public ResponseEntity<ResponseStructure<String>> updateWork( ExperianceRequestDto reqWork, int workId) throws ExperianceNotFoundException {
	              
			Optional<Experiance> optWork = workRepo.findById(workId);
			
				if (optWork.isPresent()) {
					
					Experiance workOld = optWork.get();

					Experiance workNew = convertToWork(reqWork, workOld);
					          
					          
					         if(workNew.getJobStatus()==true) {workNew.setEndDate(null);}
					          
					          Period per;
					          if( workNew.getEndDate()!=null)
					          {
					        	  
					        	  per = Period.between(workNew.getStartDate(),  workNew.getEndDate());
					        	   workNew.setYearsOfExperience(per+"");
					        	       
					          }
					         
					          else {
					        	   per = Period.between(workNew.getStartDate(),LocalDate.now());
				        	       workNew.setYearsOfExperience(per+"");
				        	      
					          }
					
					          workRepo.save(workNew);
					ResponseStructure<String> respStruc = new ResponseStructure<>();
					respStruc.setStatusCode(HttpStatus.CREATED.value());
					respStruc.setMessage(" work data updated successfully");
					respStruc.setData(" WORKEXPERIENCE UPDATED SUCCESSFULLY");
		 
					return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
				}

				else throw new ExperianceNotFoundException(" workxperince  with given Id not Present");


		}


		public ResponseEntity<ResponseStructure<ExperianceResponseDto>> findWorkByWorkId(int workId) throws ExperianceNotFoundException {
			Optional<Experiance> optWork = workRepo.findById(workId);
			
			if (optWork.isPresent()) {
				
				Experiance workOld = optWork.get();

				ExperianceResponseDto dto = convertToWorkDto(workOld);
				        HashMap<String,String> hashMap = new HashMap<>();
				        
				        hashMap.put("Employee","/resumes/"+workOld.getAssociatedResume().getResumeId());
				        dto.setOptions(hashMap);
				          
				
				
				        
				ResponseStructure<ExperianceResponseDto> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.FOUND.value());
				respStruc.setMessage(" work date fetched successfully");
				respStruc.setData(dto);
	 
				return new ResponseEntity<ResponseStructure<ExperianceResponseDto>>(respStruc, HttpStatus.FOUND);
			}

			else throw new ExperianceNotFoundException(" workxperince  with given Id not Present");

		}

		public ResponseEntity<ResponseStructure<List<ExperianceRequestDto>>> findWorkByResumeId(int resumeId) throws ResumeNotFoundException, ExperianceNotFoundException {
			                     Optional<Resume> optResume = resumRepo.findById(resumeId);
			if (optResume.isPresent()) {
				
	                          Resume resume = optResume.get();
	                          
	                          List<Experiance> workList = resume.getWorkList();
	                          if(!workList.isEmpty()) {
	                        	  
	                         List<ExperianceResponseDto> responseList = new ArrayList<>();
	                          for(Experiance wk: workList)
	                          {
	                        	  
	                        	  ExperianceResponseDto dto = convertToWorkDto(wk);
	                        	  HashMap<String,String> hashMap = new HashMap<>();
	          			        
	          			        hashMap.put("Developer","/resumes/"+wk.getAssociatedResume().getResumeId());
	                        	  dto.setOptions(hashMap);
	                        	  responseList.add(dto);
	                        	  
	                          }
	                   
				ResponseStructure<List<ExperianceRequestDto>> rs = new ResponseStructure<>();
				rs.setStatusCode(HttpStatus.FOUND.value());
				rs.setMessage(" work date fetched successfully");
				rs.setData(responseList);
				
	   return new  ResponseEntity<ResponseStructure<List<ExperianceRequestDto>>>(rs, HttpStatus.FOUND);
				
	                          }
	                          
	                          
	                          else throw new ExperianceNotFoundException("Works for this resume not present");
			}

			else throw new ResumeNotFoundException(" resume  with given Id not Present");

		}
	    
	    
	

}
