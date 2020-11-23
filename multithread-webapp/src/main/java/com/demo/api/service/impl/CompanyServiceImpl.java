package com.demo.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.demo.api.dao.CompanyDao;
import com.demo.api.entities.CompanyEntity;
import com.demo.api.mapper.CompanyMapper;
import com.demo.api.pojo.CompanyPojo;
import com.demo.api.response.dto.CompanyResponse;
import com.demo.api.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao companyDao;

	@Override
	public CompanyResponse save(CompanyPojo request) {
		CompanyEntity entity = CompanyMapper.mapRequestToEntity(request);
		if (entity == null) {
			return null;
		}
		return CompanyMapper.mapEntityToResponse(companyDao.save(entity));
	}

	@Override
	public CompanyResponse getById(Long id) {
		return CompanyMapper.mapEntityToResponse(companyDao.findById(id).orElse(null));
	}

	@Override
	public List<CompanyResponse> getAll() {
		List<CompanyEntity> all = companyDao.findAll();
		if (CollectionUtils.isEmpty(all)) {
			return null;
		}
		return all.parallelStream().map(c -> CompanyMapper.mapEntityToResponse(c)).collect(Collectors.toList());
	}

}
