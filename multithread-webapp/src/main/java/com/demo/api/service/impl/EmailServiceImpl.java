package com.demo.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.demo.api.dao.EmailDao;
import com.demo.api.entities.EmailEntity;
import com.demo.api.mapper.EmailMapper;
import com.demo.api.pojo.EmailPojo;
import com.demo.api.response.dto.EmailResponse;
import com.demo.api.service.EmailService;
import com.demo.api.threads.EmailThread;
import com.demo.api.utils.ExecutorUtils;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailDao emailDao;
	@Autowired
	private AutowireCapableBeanFactory beanFactory;

	@Override
	public EmailResponse save(EmailPojo request) {
		EmailEntity entity = EmailMapper.mapRequestToEntity(request);
		emailDao.save(entity);
		EmailThread emailThread = new EmailThread(entity.getId(), entity.getEmailId(), entity.getMsg());
		beanFactory.autowireBean(emailThread);
		ExecutorUtils.submitSingleAsycCallable(emailThread);
		return EmailMapper.mapEntityToResponse(entity);
	}

	@Override
	public EmailResponse getById(Long id) {
		return EmailMapper.mapEntityToResponse(emailDao.findById(id).orElse(null));
	}

	@Override
	public List<EmailResponse> getAll() {
		List<EmailEntity> all = emailDao.findAll();
		if (CollectionUtils.isEmpty(all)) {
			return null;
		}
		return all.parallelStream().map(e -> EmailMapper.mapEntityToResponse(e)).collect(Collectors.toList());
	}

}
