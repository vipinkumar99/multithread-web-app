package com.demo.api.threads;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.api.dao.EmailDao;
import com.demo.api.entities.EmailEntity;
import com.demo.api.external.request.ExternalEmailRequest;
import com.demo.api.external.response.ExternalResponse;
import com.demo.api.mapper.EmailMapper;
import com.demo.api.service.HttpService;

public class EmailNotificationThread extends AbstractRunnableThread {

	private final String url = "http://localhost:8081/external/api/email/save";

	@Autowired
	private EmailDao emailDao;
	@Autowired
	private HttpService httpService;

	private String emailId;
	private String msg;

	public EmailNotificationThread(String emailId, String msg) {
		super();
		this.emailId = emailId;
		this.msg = msg;
	}

	@Override
	public void preformTask() {
		EmailEntity entity = emailDao.save(EmailMapper.mapRequestToEntity(emailId, msg));
		ExternalResponse response = httpService.postRequest(url, ExternalEmailRequest.getRequest(emailId, msg));
		if (response != null && response.isSuccess()) {
			emailDao.updateStatus("SENT", entity.getId());
		} else {
			emailDao.updateStatus("FAIL", entity.getId());
		}
	}

	@Override
	public int noOfRetries() {
		return 1;
	}

}
