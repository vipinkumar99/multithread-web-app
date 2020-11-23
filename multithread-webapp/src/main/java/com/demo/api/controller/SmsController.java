package com.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.pojo.SmsPojo;
import com.demo.api.response.dto.SmsResponse;
import com.demo.api.service.SmsService;

@RestController
@RequestMapping("/sms")
public class SmsController extends AbstractController<SmsResponse, SmsPojo, SmsService> {

	@Autowired
	public SmsController(SmsService service) {
		super(service);
	}

}
