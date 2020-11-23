package com.demo.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.api.dao.PaymentDao;
import com.demo.api.dto.BaseResponse;
import com.demo.api.dto.PaymentPojo;
import com.demo.api.entities.PaymentEntity;
import com.demo.api.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	@Override
	public BaseResponse save(PaymentPojo request) throws Exception {
		Thread.sleep(2000);
		if (request == null) {
			return BaseResponse.error();
		}
		PaymentEntity entity = paymentDao
				.save(new PaymentEntity(request.getName(), request.getAmount(), request.getMode()));
		log.info("Payment adding:{}", request.getName());
		return BaseResponse.ok(entity.getId());
	}

}
