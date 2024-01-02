package com.example.jobportal.requestdto;

import org.springframework.stereotype.Component;

@Component
public class SocialProfileRequestDto {
	
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
