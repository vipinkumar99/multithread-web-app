package com.demo.api.external.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalResponse {
	private String msg;
	private String code;
	private Long txnId;

	public boolean isSuccess() {
		return this.msg.equalsIgnoreCase("success") && this.code.equals("200");
	}
}
