package com.demo.api.mapper;

import com.demo.api.entities.EmailEntity;
import com.demo.api.pojo.EmailPojo;
import com.demo.api.response.dto.EmailResponse;

public class EmailMapper extends BaseMapper {

	public static EmailEntity mapRequestToEntity(EmailPojo request) {
		if (request == null) {
			return null;
		}
		EmailEntity response = new EmailEntity();
		response.setEmailId(request.getEmailId());
		response.setMsg(request.getMsg());
		response.setStatus("PROCESS");
		return response;
	}

	public static EmailEntity mapRequestToEntity(String emailId, String msg) {
		EmailEntity response = new EmailEntity();
		response.setEmailId(emailId);
		response.setMsg(msg);
		response.setStatus("PROCESS");
		return response;
	}

	public static EmailResponse mapEntityToResponse(EmailEntity request) {
		if (request == null) {
			return null;
		}
		EmailResponse response = new EmailResponse();
		response.setEmailId(request.getEmailId());
		response.setMsg(request.getMsg());
		response.setStatus(request.getStatus());
		setResponse(response, request);
		return response;
	}
}
