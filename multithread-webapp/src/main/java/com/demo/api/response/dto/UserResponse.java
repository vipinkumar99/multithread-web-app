package com.demo.api.response.dto;

import com.demo.api.pojo.UserPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse extends UserPojo {
	private String name;
	private CompanyResponse company;
	private String mobileNo;
	private String emailId;
	private DepartmentResponse department;

}
