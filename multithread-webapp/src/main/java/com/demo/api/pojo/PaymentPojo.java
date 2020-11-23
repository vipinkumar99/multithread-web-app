package com.demo.api.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentPojo extends BasePojo {
	private Double amount;
	private String name;
	private String mode;
}
