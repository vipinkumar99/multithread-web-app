package com.demo.api.threads;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.api.dao.SmsDao;
import com.demo.api.external.request.ExternalSmsRequest;
import com.demo.api.external.response.ExternalResponse;
import com.demo.api.service.HttpService;

public class SmsThread extends AbstractCallableThread<Boolean> {

	private final String url = "http://localhost:8081/external/api/sms/save";

	@Autowired
	private SmsDao smsDao;
	@Autowired
	private HttpService httpService;

	private Long id;
	private String mobileNo;
	private String msg;

	public SmsThread(Long id, String mobileNo, String msg) {
		super();
		this.id = id;
		this.mobileNo = mobileNo;
		this.msg = msg;
	}

	@Override
	protected Boolean performTask() throws Exception {
		ExternalResponse response = httpService.postRequest(url, ExternalSmsRequest.getRequest(mobileNo, msg));
		if (response != null && response.isSuccess()) {
			smsDao.updateStatus("SENT", id);
			return true;
		}
		smsDao.updateStatus("FAIL", id);
		return false;
	}

	@Override
	protected int noOfRetries() {
		return 1;
	}

}
