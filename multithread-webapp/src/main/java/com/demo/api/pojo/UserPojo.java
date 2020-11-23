package com.demo.api.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPojo extends BasePojo {
	private String name;
	private Long companyId;
	private String mobileNo;
	private String emailId;
	private Long departmentId;
}
