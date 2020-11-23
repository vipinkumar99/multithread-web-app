package com.demo.api.external.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExternalPaymentRequest {
	private String name;
	private Double amount;
	private String mode;

	public static ExternalPaymentRequest getRequest(String name, Double amount, String mode) {
		return new ExternalPaymentRequest(name, amount, mode);
	}
}
