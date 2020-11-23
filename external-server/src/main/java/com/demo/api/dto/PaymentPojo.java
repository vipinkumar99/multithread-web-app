package com.demo.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentPojo {
	private String name;
	private Double amount;
	private String mode;
}
