package com.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.pojo.DepartmentPojo;
import com.demo.api.response.dto.DepartmentResponse;
import com.demo.api.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController extends AbstractController<DepartmentResponse, DepartmentPojo, DepartmentService> {

	@Autowired
	public DepartmentController(DepartmentService service) {
		super(service);
	}

}
