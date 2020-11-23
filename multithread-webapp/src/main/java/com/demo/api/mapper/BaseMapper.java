package com.demo.api.mapper;

import com.demo.api.entities.BaseEntity;
import com.demo.api.pojo.BasePojo;

public class BaseMapper {

	public static void setResponse(BasePojo response, BaseEntity request) {
		response.setCreated(request.getCreated());
		response.setId(request.getId());
	}
}
