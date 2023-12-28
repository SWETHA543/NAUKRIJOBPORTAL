package com.example.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobportal.entity.Company;

@Repository
public interface CompanyRepository  extends JpaRepository<Company, Integer>{

}
