package com.example.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.jobportal.entity.Skills;
@Repository
public interface SkillRepository extends JpaRepository<Skills, Integer> {
	@Query("select s from Skills s where s.skillName=?1")
	Skills findSkillByname(String skillrepo);
		

}
