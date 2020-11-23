package com.demo.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.api.external.response.ExternalResponse;
import com.demo.api.service.HttpService;

@Service
public class HttpServiceImpl implements HttpService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ExternalResponse postRequest(String url, Object request) {
		if (url == null || request == null) {
			return null;
		}
		ResponseEntity<ExternalResponse> entity = restTemplate.postForEntity(url, request, ExternalResponse.class);
		return entity.getBody();
	}

}
