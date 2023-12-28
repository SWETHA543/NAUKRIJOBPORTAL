package com.example.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobportal.entity.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer> {

}
