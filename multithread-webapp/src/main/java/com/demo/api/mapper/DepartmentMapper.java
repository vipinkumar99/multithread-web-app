package com.demo.api.mapper;

import com.demo.api.entities.DepartmentEntity;
import com.demo.api.pojo.DepartmentPojo;
import com.demo.api.response.dto.DepartmentResponse;

public class DepartmentMapper extends BaseMapper {

	public static DepartmentEntity mapRequestToEntity(DepartmentPojo request) {
		if (request == null) {
			return null;
		}
		DepartmentEntity response = new DepartmentEntity();
		response.setName(request.getName());
		return response;
	}

	public static DepartmentResponse mapEntityToResponse(DepartmentEntity request) {
		if (request == null) {
			return null;
		}
		DepartmentResponse response = new DepartmentResponse();
		response.setName(request.getName());
		setResponse(response, request);
		return response;
	}
}
