package com.demo.api.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailPojo extends BasePojo {
	private String emailId;
	private String msg;
}
