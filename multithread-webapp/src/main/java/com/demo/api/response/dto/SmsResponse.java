package com.demo.api.response.dto;

import com.demo.api.pojo.SmsPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsResponse extends SmsPojo {
	private String status;
}
