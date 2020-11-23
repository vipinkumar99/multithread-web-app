package com.demo.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.api.dao.EmailDao;
import com.demo.api.dto.BaseResponse;
import com.demo.api.dto.EmailPojo;
import com.demo.api.entities.EmailEntity;
import com.demo.api.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailDao emailDao;

	@Override
	public BaseResponse save(EmailPojo request) throws Exception {
		Thread.sleep(2000);
		if (request == null || request.getEmailId() == null || request.getMessage() == null) {
			return BaseResponse.error();
		}
		EmailEntity entity = emailDao.save(new EmailEntity(request.getEmailId(), request.getMessage()));
		log.info("Email sending to:{}", entity.getEmailId());
		return BaseResponse.ok(entity.getId());
	}

}
