package com.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.pojo.EmailPojo;
import com.demo.api.response.dto.EmailResponse;
import com.demo.api.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController extends AbstractController<EmailResponse, EmailPojo, EmailService> {

	@Autowired
	public EmailController(EmailService service) {
		super(service);
	}

}
