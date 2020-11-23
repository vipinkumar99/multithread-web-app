package com.demo.api.external.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExternalEmailRequest {
	private String emailId;
	private String message;

	public static ExternalEmailRequest getRequest(String emailId, String msg) {
		return new ExternalEmailRequest(emailId, msg);
	}
}
