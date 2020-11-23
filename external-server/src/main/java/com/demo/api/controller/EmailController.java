package com.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.dto.EmailPojo;
import com.demo.api.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController extends AbstractController<EmailPojo, EmailService> {

	@Autowired
	public EmailController(EmailService service) {
		super(service);
	}

}
