package com.example.jobportal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Resume;
import com.example.jobportal.entity.Skills;
import com.example.jobportal.entity.User;
import com.example.jobportal.enums.UserRole;
import com.example.jobportal.exceptionhandling.InvalidUserException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.exceptionhandling.SkillNotFoundException;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.SkillRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.SkillRequestDto;
import com.example.jobportal.responsedto.SkillResponseDto;
import com.example.jobportal.utility.ResponseStructure;



@Service
public class SkillService {
	@Autowired
	private SkillRepository skillRepo;
	@Autowired
	private ResumeRepository resumRepo;

	private Skills CheckSkill(String skill )
	{
		Skills oldSkill = skillRepo.findSkillByname(skill.toLowerCase());
		if(oldSkill==null) 
		{
			Skills newSkill= new Skills();
			newSkill.setSkill(skill);
			skillRepo.save(newSkill);
			return newSkill;

		}

		else return oldSkill;

	}

	private List<Skills> convertToSkill(SkillRequestDto reqDto, List<Skills> skillList)
	{    

		String[] skillsArray = reqDto.getSkill();

		for(String sk:skillsArray)
		{
			Skills Skill = CheckSkill(sk);

			if(!skillList.contains(Skill))
				skillList.add(Skill);
		}
		return skillList ;

	}

	private SkillResponseDto convertToSkillResponse(Skills skill)
	{
		SkillResponseDto dto = new SkillResponseDto();
		dto.setSkillId(skill.getSkillId());
		dto.setSkill(skill.getSkill());
		return dto;

	}

	private List<Skills> removeSkill(Skills skill,List<Skills> skillList)
	{   


		if(skill!=null)
			skillList.remove(skill);

		return skillList;



	}



	public ResponseEntity<ResponseStructure<String>> insertSkill(SkillRequestDto skillReq,int resumId) throws ResumeNotFoundException 
	{

		Optional<Resume> optResum = resumRepo.findById(resumId);

		if(optResum.isPresent()) {
			Resume resume = optResum.get();

			List<Skills> listSkills = convertToSkill(skillReq,new ArrayList<Skills>());

			resume.setLiskills(listSkills);
			resumRepo.save(resume);


			ResponseStructure<String> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.CREATED.value());
			respStruc.setMessage(" Skill data saved successfully");
			respStruc.setData("  SKILLLIST ADDED  SUCCESSFULLY");

			return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);

		}  else throw new ResumeNotFoundException("resume with given id not present");


	}


	public ResponseEntity<ResponseStructure<String>> updateSkill(SkillRequestDto skillReq,int resumId) throws ResumeNotFoundException 
	{

		Optional<Resume> optResum = resumRepo.findById(resumId);

		if(optResum.isPresent()) {
			Resume resume = optResum.get();

			List<Skills> updSkillList = convertToSkill(skillReq,resume.getLiskills());


			resume.setLiskills(updSkillList);
			resumRepo.save(resume);


			ResponseStructure<String> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.CREATED.value());
			respStruc.setMessage(" Skill data updated successfully");
			respStruc.setData("  SKILL LIST  UPDATED SUCCESSFULLY");

			return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);

		}  else throw new ResumeNotFoundException("resume with given id not present");


	}

	public ResponseEntity<ResponseStructure<String>> deleteSkillInResume(int resumeid,String skill) throws SkillNotFoundException, ResumeNotFoundException 
	{

		Optional<Resume> optRes = resumRepo.findById(resumeid);
		if(optRes.isPresent()) {
			Resume resume = optRes.get();

			Skills skilltoDel = skillRepo.findSkillByname(skill.toLowerCase());
			if (skilltoDel!=null) {


				List<Skills> updatedList = removeSkill(skilltoDel,resume.getLiskills());

				resume.setLiskills(updatedList);
				resumRepo.save(resume);

				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.ACCEPTED.value());
				respStruc.setMessage(" Skill data removed  successfully");
				respStruc.setData("  SKILL DELETED FROM LIST SUCCESSFULLY");

				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.ACCEPTED);

			}  else  throw new SkillNotFoundException("skill not present in resume");


		} else throw new ResumeNotFoundException("resume not present with this Id");

	}

	public ResponseEntity<ResponseStructure<SkillResponseDto>> findSkillByName(String skillName) throws SkillNotFoundException {
		Skills skill = skillRepo.findSkillByname(skillName.toLowerCase());  // dont forget to convertt to lower case

		if(skill!=null) {

			HashMap<String,String> hashMap = new HashMap<>();

			SkillResponseDto dto = convertToSkillResponse(skill);

			hashMap.put("Requirement", "requirement to add");
			dto.setOptions(hashMap);
			ResponseStructure<SkillResponseDto> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.FOUND.value());
			respStruc.setMessage(" Skill data fetched  successfully");
			respStruc.setData(dto);

			return new ResponseEntity<ResponseStructure<SkillResponseDto>>(respStruc, HttpStatus.FOUND);

		}  else  throw new SkillNotFoundException("skill not present in dataBase");

	}

	public ResponseEntity<ResponseStructure<List<SkillResponseDto>>> findSkillByResumeId(int resumeId) throws SkillNotFoundException {

		Optional<Resume> optResume = resumRepo.findById(resumeId);
		if(optResume.isPresent())	
		{     Resume resume = optResume.get();
		List<Skills> listSkill = resume.getLiskills();
		List<SkillResponseDto> respList = new ArrayList<>();

		for(Skills sk:listSkill) {
			HashMap<String,String> hashMap = new HashMap<>();

			SkillResponseDto dto = convertToSkillResponse(sk);

			hashMap.put("Requirement", "requirement to add");
			dto.setOptions(hashMap);
			respList.add(dto);
		}
		ResponseStructure<List<SkillResponseDto>> respStruc = new ResponseStructure<>();
		respStruc.setStatusCode(HttpStatus.FOUND.value());
		respStruc.setMessage(" Skill data fetched  successfully");
		respStruc.setData(respList);

		return new ResponseEntity<ResponseStructure<List<SkillResponseDto>>>(respStruc, HttpStatus.FOUND);

		} 
		
		else  throw new SkillNotFoundException("skill not present in dataBase");

	}		
	
}



