package com.demo.api.threads;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.api.dao.PaymentDao;
import com.demo.api.external.request.ExternalPaymentRequest;
import com.demo.api.external.response.ExternalResponse;
import com.demo.api.service.HttpService;

public class PaymentThread extends AbstractCallableThread<Boolean> {
	private final String url = "http://localhost:8081/external/api/payment/save";

	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private HttpService httpService;

	private Long id;
	private String name;
	private Double amount;
	private String mode;

	public PaymentThread(Long id, String name, Double amount, String mode) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.mode = mode;
	}

	@Override
	protected Boolean performTask() throws Exception {
		ExternalResponse response = httpService.postRequest(url, ExternalPaymentRequest.getRequest(name, amount, mode));
		if (response != null) {
			paymentDao.updateStatus(response.getMsg(), response.getCode(), id);
			return true;
		}
		paymentDao.updateStatus("TIME_OUT", "524", id);
		return false;
	}

	@Override
	protected int noOfRetries() {
		return 1;
	}

}
