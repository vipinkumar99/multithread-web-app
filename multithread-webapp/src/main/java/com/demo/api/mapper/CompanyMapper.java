package com.demo.api.mapper;

import com.demo.api.entities.CompanyEntity;
import com.demo.api.pojo.CompanyPojo;
import com.demo.api.response.dto.CompanyResponse;

public class CompanyMapper extends BaseMapper {

	public static CompanyEntity mapRequestToEntity(CompanyPojo request) {
		if (request == null) {
			return null;
		}
		CompanyEntity response = new CompanyEntity();
		response.setName(request.getName());
		return response;
	}

	public static CompanyResponse mapEntityToResponse(CompanyEntity request) {
		if (request == null) {
			return null;
		}
		CompanyResponse response = new CompanyResponse();
		response.setName(request.getName());
		setResponse(response, request);
		return response;
	}
}
