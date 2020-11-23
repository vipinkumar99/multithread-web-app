package com.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.pojo.CompanyPojo;
import com.demo.api.response.dto.CompanyResponse;
import com.demo.api.service.CompanyService;

@RestController
@RequestMapping("/company")
public class ComapnyController extends AbstractController<CompanyResponse, CompanyPojo, CompanyService> {

	@Autowired
	public ComapnyController(CompanyService service) {
		super(service);
	}

}
