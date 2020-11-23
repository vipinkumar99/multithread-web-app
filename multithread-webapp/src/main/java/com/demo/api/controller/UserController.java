package com.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.api.pojo.UserPojo;
import com.demo.api.response.dto.UserResponse;
import com.demo.api.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<UserResponse, UserPojo, UserService> {

	private final UserService userService;

	@Autowired
	public UserController(UserService service) {
		super(service);
		this.userService = service;
	}

	@PostMapping("/save")
	public ResponseEntity<Boolean> save(@RequestParam MultipartFile file) {
		return ResponseEntity.ok(userService.save(file));
	}

	@GetMapping("/send")
	public ResponseEntity<Boolean> send() {
		return ResponseEntity.ok(userService.sendMsg());
	}
	
	@GetMapping("/updateList")
	public ResponseEntity<Boolean> updateList() {
		return ResponseEntity.ok(userService.updateList());
	}
}
