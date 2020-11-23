package com.demo.api.threads;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.api.dao.EmailDao;
import com.demo.api.external.request.ExternalEmailRequest;
import com.demo.api.external.response.ExternalResponse;
import com.demo.api.service.HttpService;

public class EmailThread extends AbstractCallableThread<Boolean> {

	private final String url = "http://localhost:8081/external/api/email/save";

	@Autowired
	private EmailDao emailDao;
	@Autowired
	private HttpService httpService;

	private Long id;
	private String emailId;
	private String msg;

	public EmailThread(Long id, String emailId, String msg) {
		super();
		this.id = id;
		this.emailId = emailId;
		this.msg = msg;
	}

	@Override
	protected Boolean performTask() throws Exception {
		ExternalResponse response = httpService.postRequest(url, ExternalEmailRequest.getRequest(emailId, msg));
		if (response != null && response.isSuccess()) {
			emailDao.updateStatus("SENT", id);
			return true;
		}
		emailDao.updateStatus("FAIL", id);
		return false;
	}

	@Override
	protected int noOfRetries() {
		return 1;
	}

}
