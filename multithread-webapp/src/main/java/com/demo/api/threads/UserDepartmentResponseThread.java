package com.demo.api.threads;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.api.response.dto.DepartmentResponse;
import com.demo.api.response.dto.UserResponse;
import com.demo.api.service.DepartmentService;

public class UserDepartmentResponseThread implements Runnable {

	@Autowired
	private DepartmentService departmentService;

	private List<UserResponse> users;
	private CountDownLatch latch;

	public UserDepartmentResponseThread(List<UserResponse> users, CountDownLatch latch) {
		super();
		this.users = users;
		this.latch = latch;
	}

	@Override
	public void run() {
		Map<Long, DepartmentResponse> map = new HashMap<>();
		for (UserResponse user : users) {
			if (user.getDepartmentId() != null) {
				if (map.containsKey(user.getDepartmentId())) {
					user.setDepartment(map.get(user.getDepartmentId()));
				} else {
					DepartmentResponse department = departmentService.getById(user.getDepartmentId());
					user.setDepartment(department);
					map.put(department.getId(), department);
				}
			}
		}
		latch.countDown();
	}

}
