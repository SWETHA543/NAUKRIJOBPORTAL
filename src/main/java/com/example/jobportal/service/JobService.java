package com.example.jobportal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.Job;
import com.example.jobportal.exceptionhandling.CompanyNotFoundException;
import com.example.jobportal.exceptionhandling.JobNotFoundById;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.JobRepository;
import com.example.jobportal.requestdto.JobRequestDto;
import com.example.jobportal.responsedto.JobResponseDto;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class JobService {
	
	@Autowired
	JobRepository jobrepository;
   
	@Autowired
	CompanyRepository companyRepository;

	private Job convertToJob(JobRequestDto jobrequestdto, Job job) {
		
		job.setCtc(jobrequestdto.getCtc());
		job.setDesignation(jobrequestdto.getDesignation());
		job.setJobRole(jobrequestdto.getJobRole());
		job.setLocation(jobrequestdto.getLocation());
		
		return job;
	
	}

	private JobResponseDto convertToJobResponse(Job job) {
		
		 
		  JobResponseDto jobResponseDto = new JobResponseDto() ;
		  jobResponseDto.setCtc(job.getCtc());
		  jobResponseDto.setDesignation(job.getDesignation());
		  jobResponseDto.setJobId(job.getJobId());
		  jobResponseDto.setJobRole(job.getJobRole());
		  jobResponseDto.setLocation(job.getLocation());
	 return jobResponseDto;

	}
	
	
	

	public ResponseEntity<ResponseStructure<String>> insertJOb(  JobRequestDto jobrequestdto, int companyId) throws CompanyNotFoundException {
		
	                Optional<Company> findById = companyRepository.findById(companyId);

		if (findById.isPresent()) {
			Company company = findById.get();
			  
			      Job job = convertToJob(jobrequestdto,new Job());
			      job.setCompMap(company);
			      
                  jobrepository.save(job);
		

				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" Company data saved successfully");
				respStruc.setData(" 1 COMPANY ADDED  SUCCESSFULLY");

				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
			}

			else throw new CompanyNotFoundException(" Company required to add Jobs");
			

	
			
	}

	public ResponseEntity<ResponseStructure<JobResponseDto>> findByIdService(int id) {
		
		       Optional<Job> id2 = jobrepository.findById(id);
		       
		       if(id2.isPresent())
		       {
		    	        Job job = id2.get();
		    	        
		    	        JobResponseDto id3 = convertToJobResponse(job);
		    	        ResponseStructure<JobResponseDto> rs=new ResponseStructure<JobResponseDto>();
		    	        rs.setStatusCode(HttpStatus.FOUND.value());
		    	        rs.setData(id3);
		    	        rs.setMessage("data fetched successfully");
		    	        
		    	        return   new ResponseEntity<ResponseStructure<JobResponseDto>>(rs, HttpStatus.FOUND);
		    	        
		       }
		       else throw new JobNotFoundById("job not found by Id");
		
		
		
	}

	public ResponseEntity<ResponseStructure<JobResponseDto>> updateJobService(JobRequestDto li, int id) {
		
		
		                Optional<Job> findById = jobrepository.findById(id);
		                
		                
		                if(findById.isPresent())
		                {
		                	      Job existing = findById.get();
		                	      
		                	          Job updated = convertToJob(li,existing);
		                	          jobrepository.save(updated);
		                	          ResponseStructure<JobResponseDto> rs=new ResponseStructure<JobResponseDto>();
		                	          rs.setStatusCode(HttpStatus.FOUND.value());
		                	          rs.setMessage("job details updated successfully");
		                	          rs.setData(convertToJobResponse(updated));
		                	          
		                	          return new ResponseEntity<ResponseStructure<JobResponseDto>>(rs, HttpStatus.FOUND);
		                }
		                else throw new JobNotFoundById("jon not found by this Id to update the details");
	}

	public ResponseEntity<ResponseStructure<String>> deleteJobService(int id) {
		
		            Optional<Job> findById = jobrepository.findById(id);
		            
		            if(findById.isPresent())
		            {
		            	jobrepository.deleteById(id);
		            	ResponseStructure<String> rs=new ResponseStructure<String>();
		            	
		            	rs.setStatusCode(HttpStatus.FOUND.value());
		            	rs.setData("job data deleted Successfully");
		            	rs.setMessage("Job deleted successfully");
		            	
		            	return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.FOUND);
		            	
		            	
		            }
		            else throw new JobNotFoundById("Job not found by this Id to deleted please try again ");
	
	}
	
	


}
