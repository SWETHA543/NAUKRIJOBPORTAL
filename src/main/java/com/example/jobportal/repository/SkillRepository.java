package com.example.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobportal.entity.Skills;

public interface SkillRepository extends JpaRepository<Skills, Integer> {

		Skills findSkillByname(String lowerCase);

}
