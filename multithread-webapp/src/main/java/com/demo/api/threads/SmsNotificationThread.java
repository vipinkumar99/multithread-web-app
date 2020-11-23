package com.demo.api.threads;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.api.dao.SmsDao;
import com.demo.api.entities.SmsEntity;
import com.demo.api.external.request.ExternalSmsRequest;
import com.demo.api.external.response.ExternalResponse;
import com.demo.api.mapper.SmsMapper;
import com.demo.api.service.HttpService;

public class SmsNotificationThread extends AbstractRunnableThread {

	private final String url = "http://localhost:8081/external/api/sms/save";

	@Autowired
	private SmsDao smsDao;

	@Autowired
	private HttpService httpService;

	private String mobileNo;
	private String msg;

	public SmsNotificationThread(String mobileNo, String msg) {
		super();
		this.mobileNo = mobileNo;
		this.msg = msg;
	}

	@Override
	public void preformTask() {
		SmsEntity entity = smsDao.save(SmsMapper.mapRequestToEntity(mobileNo, msg));
		ExternalResponse response = httpService.postRequest(url, ExternalSmsRequest.getRequest(mobileNo, msg));
		if (response != null && response.isSuccess()) {
			smsDao.updateStatus("SENT", entity.getId());
		} else {
			smsDao.updateStatus("FAIL", entity.getId());
		}
	}

	@Override
	public int noOfRetries() {
		return 1;
	}

}
