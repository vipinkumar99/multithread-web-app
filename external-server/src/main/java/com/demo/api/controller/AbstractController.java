package com.demo.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.api.dto.BaseResponse;
import com.demo.api.service.AbstractService;

public class AbstractController<S, T extends AbstractService<S>> {

	private T service;

	public AbstractController(T service) {
		super();
		this.service = service;
	}

	@PostMapping("/save")
	public ResponseEntity<BaseResponse> save(@RequestBody S request) throws Exception {
		return ResponseEntity.ok(service.save(request));
	}

}
