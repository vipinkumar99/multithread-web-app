package com.demo.api.mapper;

import com.demo.api.entities.UserEntity;
import com.demo.api.pojo.UserPojo;
import com.demo.api.response.dto.UserResponse;

public class UserMapper extends BaseMapper {

	public static UserEntity mapRequestToEntity(UserPojo request) {
		if (request == null) {
			return null;
		}
		
		UserEntity response = new UserEntity();
		response.setName(request.getName());
		response.setCompanyId(request.getCompanyId());
		response.setMobileNo(request.getMobileNo());
		response.setEmailId(request.getEmailId());
		response.setDepartmentId(request.getDepartmentId());
		return response;
	}

	public static UserResponse mapEntityToResponse(UserEntity request) {
		if (request == null) {
			return null;
		}
		UserResponse response = new UserResponse();
		response.setName(request.getName());
		response.setCompanyId(request.getCompanyId());
		response.setMobileNo(request.getMobileNo());
		response.setEmailId(request.getEmailId());
		response.setDepartmentId(request.getDepartmentId());
		setResponse(response, request);
		return response;
	}
}
