package com.example.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobportal.entity.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {

}
