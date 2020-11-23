package com.demo.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.demo.api.dao.DepartmentDao;
import com.demo.api.entities.DepartmentEntity;
import com.demo.api.mapper.DepartmentMapper;
import com.demo.api.pojo.DepartmentPojo;
import com.demo.api.response.dto.DepartmentResponse;
import com.demo.api.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public DepartmentResponse save(DepartmentPojo request) {
		DepartmentEntity entity = DepartmentMapper.mapRequestToEntity(request);
		if (entity == null) {
			return null;
		}
		return DepartmentMapper.mapEntityToResponse(departmentDao.save(entity));
	}

	@Override
	public DepartmentResponse getById(Long id) {
		return DepartmentMapper.mapEntityToResponse(departmentDao.findById(id).orElse(null));
	}

	@Override
	public List<DepartmentResponse> getAll() {
		List<DepartmentEntity> all = departmentDao.findAll();
		if (CollectionUtils.isEmpty(all)) {
			return null;
		}
		return all.stream().map(d -> DepartmentMapper.mapEntityToResponse(d)).collect(Collectors.toList());
	}

}
