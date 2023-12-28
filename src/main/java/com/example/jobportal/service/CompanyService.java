package com.example.jobportal.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.User;
import com.example.jobportal.enums.BusinessType;
import com.example.jobportal.enums.UserRole;
import com.example.jobportal.exceptionhandling.CompanyNotFoundException;
import com.example.jobportal.exceptionhandling.IllegalAccssException;
import com.example.jobportal.exceptionhandling.UserNotFoundException;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.CompanyRequestDto;
import com.example.jobportal.responsedto.CompanyResponseDto;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class CompanyService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CompanyRepository companyRepository;

	private Company convertToCompany(CompanyRequestDto companyRequestdto, Company company) {
		
		company.setCompanyName(companyRequestdto.getCompanyName());
		company.setContactEmail(companyRequestdto.getContactEmail());
		company.setContactPhno(companyRequestdto.getContactPhno());
		company.setDescription(companyRequestdto.getDescription());
		company.setFoundedDate(companyRequestdto.getFoundedDate());
		company.setWebsite(companyRequestdto.getWebsite());

		return company;
	}

	private CompanyResponseDto convertToCompResponse(Company company) {
		CompanyResponseDto companyResponseDto = new CompanyResponseDto();
		companyResponseDto.setBusinessType(company.getBusinessType());
		companyResponseDto.setCompanyId(company.getCompanyId());
		companyResponseDto.setCompanyName(company.getCompanyName());
		companyResponseDto.setContactEmail(company.getContactEmail());
		companyResponseDto.setContactPhno(company.getDescription());
		companyResponseDto.setDescription(company.getDescription());
		companyResponseDto.setFoundedDate(company.getFoundedDate());
		companyResponseDto.setWebsite(company.getWebsite());
		return companyResponseDto;

	}

	public ResponseEntity<ResponseStructure<String>> insertCompany(CompanyRequestDto companyRequestdto, BusinessType bussinessType,
			int userId) throws IllegalAccssException, UserNotFoundException {
		Optional<User> findById = userRepository.findById(userId);

		if (findById.isPresent()) {
			User user = findById.get();
			if (user.getUserRole() == UserRole.EMPLOYER) {

				Company company = convertToCompany(companyRequestdto, new Company());
				company.setBusinessType(bussinessType);
				company.setUserMap(user);

				companyRepository.save(company);

				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" Company data saved successfully");
				respStruc.setData(" 1 COMPANY ADDED  SUCCESSFULLY");

				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
			}

			else throw new IllegalAccssException("user not authorised to do this opertation");
			

		} else
			throw new UserNotFoundException("user not found");
	}

	public ResponseEntity<ResponseStructure<CompanyResponseDto>> findCompById(int companyId) throws CompanyNotFoundException {
		
		Optional<Company> optComp = companyRepository.findById(companyId);
		if (optComp.isPresent()) {
			Company company = optComp.get();
			
			
;			 CompanyResponseDto dto = convertToCompResponse(company); 
			 HashMap<String,String> hasmap = new HashMap<>();
			 hasmap.put("Founder", "/users/"+company.getUserMap().getUserId());
			 dto.setOptions(hasmap);

			ResponseStructure<CompanyResponseDto> responseStruct = new ResponseStructure<CompanyResponseDto>();
			responseStruct.setMessage(" company found successfully");
			responseStruct.setStatusCode(HttpStatus.FOUND.value());
			responseStruct.setData(dto);

			return new ResponseEntity<ResponseStructure<CompanyResponseDto>>(responseStruct, HttpStatus.FOUND);

		}

		else
			throw new CompanyNotFoundException(" company  with the given  Id not present");

	}

	public ResponseEntity<ResponseStructure<String>> updateCompany(CompanyRequestDto companyRequestdto, int companyId) throws CompanyNotFoundException {
		
		
		Optional<Company> findById = companyRepository.findById(companyId);
		if(findById.isPresent()) {
		
		
		Company company = convertToCompany(companyRequestdto,findById.get());
	

		companyRepository.save(company);

		ResponseStructure<String> respStruc = new ResponseStructure<>();
		respStruc.setStatusCode(HttpStatus.ACCEPTED.value());
		respStruc.setMessage(" Company data saved successfully");
		respStruc.setData("  COMPANY UPDATED SUCCESSFULLY");

		return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.ACCEPTED);
		
		
		} else throw new CompanyNotFoundException(" company not found with given Id");
	}

	public ResponseEntity<ResponseStructure<String>> deleteCompById(int companyId) throws CompanyNotFoundException {
		
		
		Optional<Company> findById = companyRepository.findById(companyId);
		if (findById.isPresent()) {
			
			
			companyRepository.deleteById(companyId);
;			

			ResponseStructure<String> responseStruct = new ResponseStructure<>();
			responseStruct.setMessage(" company found successfully");
			responseStruct.setStatusCode(HttpStatus.FOUND.value());
			responseStruct.setData("COMPANY DELETED SUCCESSFULLY");

			return new ResponseEntity<ResponseStructure<String>>(responseStruct, HttpStatus.FOUND);

		}

		else
			throw new CompanyNotFoundException(" company  with the given  Id not present");

	}
	


}
