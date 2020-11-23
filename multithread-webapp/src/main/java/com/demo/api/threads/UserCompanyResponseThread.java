package com.demo.api.threads;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.api.response.dto.CompanyResponse;
import com.demo.api.response.dto.UserResponse;
import com.demo.api.service.CompanyService;

public class UserCompanyResponseThread implements Runnable {

	@Autowired
	private CompanyService companyService;

	private List<UserResponse> users;
	private CountDownLatch latch;

	public UserCompanyResponseThread(List<UserResponse> users, CountDownLatch latch) {
		super();
		this.users = users;
		this.latch = latch;
	}

	@Override
	public void run() {
		Map<Long, CompanyResponse> map = new HashMap<>();
		for (UserResponse user : users) {
			if (user.getCompanyId() != null) {
				if (map.containsKey(user.getCompanyId())) {
					user.setCompany(map.get(user.getCompanyId()));
				} else {
					CompanyResponse company = companyService.getById(user.getCompanyId());
					user.setCompany(company);
					map.put(company.getId(), company);
				}
			}
		}
		latch.countDown();
	}

}
