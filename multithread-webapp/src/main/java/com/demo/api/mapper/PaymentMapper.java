package com.demo.api.mapper;

import com.demo.api.entities.PaymentEntity;
import com.demo.api.pojo.PaymentPojo;
import com.demo.api.response.dto.PaymentResponse;

public class PaymentMapper extends BaseMapper {

	public static PaymentEntity mapRequestToEntity(PaymentPojo request) {
		if (request == null) {
			return null;
		}
		PaymentEntity response = new PaymentEntity();
		response.setAmount(request.getAmount());
		response.setMode(request.getMode());
		response.setName(request.getName());
		return response;
	}

	public static PaymentResponse mapEntityToResponse(PaymentEntity request) {
		if (request == null) {
			return null;
		}
		PaymentResponse response = new PaymentResponse();
		response.setAmount(request.getAmount());
		response.setMode(request.getMode());
		response.setName(request.getName());
		response.setExCode(request.getExCode());
		response.setExStatus(request.getExStatus());
		setResponse(response, request);
		return response;
	}
}
