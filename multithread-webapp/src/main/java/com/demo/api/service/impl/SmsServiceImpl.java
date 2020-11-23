package com.demo.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.demo.api.dao.SmsDao;
import com.demo.api.entities.SmsEntity;
import com.demo.api.mapper.SmsMapper;
import com.demo.api.pojo.SmsPojo;
import com.demo.api.response.dto.SmsResponse;
import com.demo.api.service.SmsService;
import com.demo.api.threads.SmsThread;
import com.demo.api.utils.ExecutorUtils;

@Service
public class SmsServiceImpl implements SmsService {

	@Autowired
	private SmsDao smsDao;
	@Autowired
	private AutowireCapableBeanFactory beanFactory;

	@Override
	public SmsResponse save(SmsPojo request) {
		SmsEntity entity = SmsMapper.mapRequestToEntity(request);
		if (entity == null) {
			return null;
		}
		smsDao.save(entity);
		SmsThread smsThread = new SmsThread(entity.getId(), entity.getMobileNo(), entity.getMsg());
		beanFactory.autowireBean(smsThread);
		ExecutorUtils.submitSingleAsycCallable(smsThread);
		return SmsMapper.mapEntityToResponse(entity);
	}

	@Override
	public boolean sendSms(List<SmsPojo> requestList) {
		if (CollectionUtils.isEmpty(requestList)) {
			return false;
		}
		List<SmsThread> smsThreads = new ArrayList<>();
		for (SmsPojo request : requestList) {
			SmsEntity entity = SmsMapper.mapRequestToEntity(request);
			if (entity != null) {
				smsDao.save(entity);
				SmsThread smsThread = new SmsThread(entity.getId(), entity.getMobileNo(), entity.getMsg());
				beanFactory.autowireBean(smsThread);
				smsThreads.add(smsThread);
			}
		}
		if (!CollectionUtils.isEmpty(smsThreads)) {
			ExecutorUtils.submitAsycCallables(smsThreads);
		}
		return true;
	}

	@Override
	public SmsResponse getById(Long id) {
		return SmsMapper.mapEntityToResponse(smsDao.findById(id).orElse(null));
	}

	@Override
	public List<SmsResponse> getAll() {
		List<SmsEntity> all = smsDao.findAll();
		if (CollectionUtils.isEmpty(all)) {
			return null;
		}
		return all.parallelStream().map(s -> SmsMapper.mapEntityToResponse(s)).collect(Collectors.toList());
	}

}
