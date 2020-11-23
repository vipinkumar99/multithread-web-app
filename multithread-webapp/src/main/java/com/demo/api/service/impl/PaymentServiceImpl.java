package com.demo.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.demo.api.dao.PaymentDao;
import com.demo.api.entities.PaymentEntity;
import com.demo.api.mapper.PaymentMapper;
import com.demo.api.pojo.PaymentPojo;
import com.demo.api.response.dto.PaymentResponse;
import com.demo.api.service.PaymentService;
import com.demo.api.threads.PaymentThread;
import com.demo.api.utils.ExecutorUtils;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private AutowireCapableBeanFactory beanFactory;

	@Override
	public PaymentResponse save(PaymentPojo request) {
		PaymentEntity entity = PaymentMapper.mapRequestToEntity(request);
		if (entity == null) {
			return null;
		}
		paymentDao.save(entity);
		PaymentThread paymentThread = new PaymentThread(entity.getId(), entity.getName(), entity.getAmount(),
				entity.getMode());
		beanFactory.autowireBean(paymentThread);
		ExecutorUtils.submitSingleAsycCallable(paymentThread);
		return PaymentMapper.mapEntityToResponse(entity);
	}

	@Override
	public PaymentResponse getById(Long id) {
		return PaymentMapper.mapEntityToResponse(paymentDao.findById(id).orElse(null));
	}

	@Override
	public List<PaymentResponse> getAll() {
		List<PaymentEntity> all = paymentDao.findAll();
		if (CollectionUtils.isEmpty(all)) {
			return null;
		}
		return all.parallelStream().map(p -> PaymentMapper.mapEntityToResponse(p)).collect(Collectors.toList());
	}

}
