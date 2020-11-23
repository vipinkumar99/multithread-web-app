package com.demo.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.api.service.AbstractService;

public class AbstractController<R, S, T extends AbstractService<R, S>> {

	private T service;

	public AbstractController(T service) {
		super();
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<R> save(@RequestBody S request) {
		return ResponseEntity.ok(service.save(request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<R> getById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.getById(id));
	}

	@GetMapping
	public ResponseEntity<List<R>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

}
