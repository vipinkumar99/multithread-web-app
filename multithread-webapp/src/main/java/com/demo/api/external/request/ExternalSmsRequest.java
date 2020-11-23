package com.demo.api.external.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExternalSmsRequest {
	private String mobileNo;
	private String message;

	public static ExternalSmsRequest getRequest(String mobileNo, String msg) {
		return new ExternalSmsRequest(mobileNo, msg);
	}
}
