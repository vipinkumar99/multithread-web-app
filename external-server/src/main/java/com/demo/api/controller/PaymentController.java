package com.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.dto.PaymentPojo;
import com.demo.api.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController extends AbstractController<PaymentPojo, PaymentService> {

	@Autowired
	public PaymentController(PaymentService service) {
		super(service);
	}

}
