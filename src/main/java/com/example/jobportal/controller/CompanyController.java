package com.example.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.enums.BusinessType;
import com.example.jobportal.exceptionhandling.CompanyNotFoundException;
import com.example.jobportal.exceptionhandling.IllegalAccssException;
import com.example.jobportal.exceptionhandling.UserNotFoundException;
import com.example.jobportal.requestdto.CompanyRequestDto;
import com.example.jobportal.responsedto.CompanyResponseDto;
import com.example.jobportal.service.CompanyService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;


@RestController
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@PostMapping("/users/{userId}/Business-Type/{bussiness}/companies")
	public ResponseEntity<ResponseStructure<String>> inserCompany(@RequestBody  CompanyRequestDto compReq,
			@PathVariable int userId, @PathVariable BusinessType bussiness)
			throws IllegalAccssException, UserNotFoundException {

		return companyService.insertCompany(compReq, bussiness, userId);

	}

	@GetMapping("/companies/{companyId}")
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> findCompanyById(@PathVariable int companyId)
			throws CompanyNotFoundException {

		return companyService.findCompById(companyId);

	}

	@PutMapping("/companies/{companyId}")
	public ResponseEntity<ResponseStructure<String>> updateUser(@RequestBody  CompanyRequestDto companyRequestdto,
			@PathVariable int companyId) throws CompanyNotFoundException {

		return companyService.updateCompany(companyRequestdto, companyId);

	}

	@DeleteMapping("/companies/{companyId}")
	public ResponseEntity<ResponseStructure<String>> deleteCompById(@PathVariable int companyId)
			throws CompanyNotFoundException {

		return companyService.deleteCompById(companyId);

	}


}
