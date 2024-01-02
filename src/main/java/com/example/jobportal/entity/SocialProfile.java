package com.example.jobportal.entity;

import org.hibernate.annotations.GeneratorType;
import org.springframework.stereotype.Component;

import com.example.jobportal.enums.SocialType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Component
public class SocialProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int socialId;
	private SocialType socialtype;
	private String url;
	
	@ManyToOne
	private Resume res;

	public int getSocialId() {
		return socialId;
	}

	public void setSocialId(int socialId) {
		this.socialId = socialId;
	}

	public SocialType getSocialtype() {
		return socialtype;
	}

	public void setSocialtype(SocialType socialtype) {
		this.socialtype = socialtype;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Resume getRes() {
		return res;
	}

	public void setRes(Resume res) {
		this.res = res;
	}
	
	
	

}
