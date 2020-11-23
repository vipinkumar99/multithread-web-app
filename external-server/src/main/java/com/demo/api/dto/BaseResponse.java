package com.demo.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = Include.NON_NULL)
public class BaseResponse {
	private String code;
	private String msg;
	private Long txnId;

	public static BaseResponse error() {
		BaseResponse response = new BaseResponse();
		response.setCode("200");
		response.setMsg("Fail");
		return response;
	}

	public static BaseResponse ok(Long txnId) {
		BaseResponse response = new BaseResponse();
		response.setCode("200");
		response.setMsg("success");
		response.setTxnId(txnId);
		return response;
	}
}
