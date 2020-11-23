package com.demo.api.response.dto;

import com.demo.api.pojo.PaymentPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse extends PaymentPojo {
	private String exStatus;
	private String exCode;
}
