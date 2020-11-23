package com.demo.api.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsPojo extends BasePojo {
	private String mobileNo;
	private String msg;

}
