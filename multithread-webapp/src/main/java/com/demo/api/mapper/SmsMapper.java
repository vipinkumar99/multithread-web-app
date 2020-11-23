package com.demo.api.mapper;

import com.demo.api.entities.SmsEntity;
import com.demo.api.pojo.SmsPojo;
import com.demo.api.response.dto.SmsResponse;

public class SmsMapper extends BaseMapper {

	public static SmsEntity mapRequestToEntity(SmsPojo request) {
		if (request == null) {
			return null;
		}
		SmsEntity response = new SmsEntity();
		response.setMsg(request.getMsg());
		response.setMobileNo(request.getMobileNo());
		response.setStatus("PROCESS");
		return response;
	}

	public static SmsEntity mapRequestToEntity(String mobileNo, String msg) {
		SmsEntity response = new SmsEntity();
		response.setMsg(msg);
		response.setMobileNo(mobileNo);
		response.setStatus("PROCESS");
		return response;
	}

	public static SmsResponse mapEntityToResponse(SmsEntity request) {
		if (request == null) {
			return null;
		}
		SmsResponse response = new SmsResponse();
		response.setMobileNo(request.getMobileNo());
		response.setMsg(request.getMsg());
		response.setStatus(request.getStatus());
		setResponse(response, request);
		return response;
	}
}
