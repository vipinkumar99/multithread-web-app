package com.demo.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.api.dao.SmsDao;
import com.demo.api.dto.BaseResponse;
import com.demo.api.dto.SmsPojo;
import com.demo.api.entities.SmsEntity;
import com.demo.api.service.SmsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

	@Autowired
	private SmsDao smsDao;

	@Override
	public BaseResponse save(SmsPojo request) throws Exception {
		Thread.sleep(2000);
		if (request == null || request.getMobileNo() == null || request.getMessage() == null) {
			return BaseResponse.error();
		}
		SmsEntity entity = smsDao.save(new SmsEntity(request.getMobileNo(), request.getMessage()));
		log.info("Sms sending to:{}", entity.getMobileNo());
		return BaseResponse.ok(entity.getId());
	}

}
