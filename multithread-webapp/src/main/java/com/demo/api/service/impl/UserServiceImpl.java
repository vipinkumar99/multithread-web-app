package com.demo.api.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.demo.api.dao.UserDao;
import com.demo.api.entities.UserEntity;
import com.demo.api.mapper.UserMapper;
import com.demo.api.pojo.SmsPojo;
import com.demo.api.pojo.UserPojo;
import com.demo.api.response.dto.UserResponse;
import com.demo.api.service.CompanyService;
import com.demo.api.service.DepartmentService;
import com.demo.api.service.SmsService;
import com.demo.api.service.UserService;
import com.demo.api.threads.EmailNotificationThread;
import com.demo.api.threads.InsertCompanyThread;
import com.demo.api.threads.InsertDepartmentThread;
import com.demo.api.threads.InsertUserThread;
import com.demo.api.threads.SmsNotificationThread;
import com.demo.api.threads.UserCompanyResponseThread;
import com.demo.api.threads.UserCompanyThread;
import com.demo.api.threads.UserDepartmentResponseThread;
import com.demo.api.threads.UserDepartmentThread;
import com.demo.api.utils.ExecutorUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private AutowireCapableBeanFactory beanFactory;
	@Autowired
	private SmsService smsService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DepartmentService departmentService;

	@Override
	public boolean save(MultipartFile file) {
		Set<UserEntity> users = parseCsvFile(file);
		if (CollectionUtils.isEmpty(users)) {
			return false;
		}
		Set<String> companies = new HashSet<>();
		Set<String> departments = new HashSet<>();
		users.forEach(user -> {
			if (user.getCom() != null) {
				companies.add(user.getCom());
			}
			if (user.getDep() != null) {
				departments.add(user.getDep());
			}
		});
		InsertCompanyThread companyThread = new InsertCompanyThread(companies);
		beanFactory.autowireBean(companyThread);
		ExecutorUtils.executeSingleRunnable(companyThread);

		InsertDepartmentThread departmentThread = new InsertDepartmentThread(departments);
		beanFactory.autowireBean(departmentThread);
		ExecutorUtils.executeSingleRunnable(departmentThread);

		InsertUserThread userThread = new InsertUserThread(users);
		beanFactory.autowireBean(userThread);
		ExecutorUtils.executeSingleRunnable(userThread);
		return true;
	}

	private Set<UserEntity> parseCsvFile(final MultipartFile file) {
		final Set<UserEntity> users = new HashSet<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(",");
				if (split != null && split.length >= 6) {
					users.add(new UserEntity(split[0], split[1], split[2], split[3], split[5]));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public boolean sendMsg() {
		List<UserEntity> users = userDao.findAll();
		List<SmsPojo> requestList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(users)) {
			for (UserEntity user : users) {
				SmsPojo request = new SmsPojo();
				request.setMobileNo(user.getMobileNo());
				request.setMsg("Hello");
				requestList.add(request);
			}
		}
		smsService.sendSms(requestList);
		return true;
	}

	@Override
	public UserResponse save(UserPojo request) {
		UserEntity entity = UserMapper.mapRequestToEntity(request);
		if (entity == null) {
			return null;
		}
		userDao.save(entity);
		SmsNotificationThread smsThread = new SmsNotificationThread(entity.getMobileNo(), "Hi, Welcome to test!");
		beanFactory.autowireBean(smsThread);
		ExecutorUtils.executeSingleAsycRunnable(smsThread);
		EmailNotificationThread emailThread = new EmailNotificationThread(entity.getEmailId(), "Hi, Welcome to test!");
		beanFactory.autowireBean(emailThread);
		ExecutorUtils.executeSingleAsycRunnable(emailThread);
		return UserMapper.mapEntityToResponse(entity);
	}

	@Override
	public UserResponse getById(Long id) {
		return getResponse(userDao.findById(id).orElse(null));
	}

	@Override
	public List<UserResponse> getAll() {
		return getResponseList(userDao.findAll());
	}

	@Override
	public boolean updateList() {
		List<UserEntity> users = userDao.findAll();
		if (CollectionUtils.isEmpty(users)) {
			return false;
		}
		UserCompanyThread userCompanyThread = new UserCompanyThread(users);
		beanFactory.autowireBean(userCompanyThread);
		UserDepartmentThread userDepartmentThread = new UserDepartmentThread(users);
		beanFactory.autowireBean(userDepartmentThread);
		ExecutorUtils.executeRunnables(Arrays.asList(userCompanyThread, userDepartmentThread), 2);
		return true;
	}

	private List<UserResponse> getResponseList(List<UserEntity> entities) {
		if (CollectionUtils.isEmpty(entities)) {
			return new ArrayList<>();
		}
		List<UserResponse> responseList = entities.parallelStream().map(UserMapper::mapEntityToResponse)
				.collect(Collectors.toList());
		if (CollectionUtils.isEmpty(responseList)) {
			return new ArrayList<>();
		}
		CountDownLatch latch = new CountDownLatch(2);
		UserCompanyResponseThread userCompanyResponseThread = new UserCompanyResponseThread(responseList, latch);
		beanFactory.autowireBean(userCompanyResponseThread);
		UserDepartmentResponseThread userDepartmentResponseThread = new UserDepartmentResponseThread(responseList,
				latch);
		beanFactory.autowireBean(userDepartmentResponseThread);
		ExecutorUtils.executeRunnables(Arrays.asList(userCompanyResponseThread, userDepartmentResponseThread), 2);
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return responseList;
	}

	private UserResponse getResponse(UserEntity entity) {
		UserResponse response = UserMapper.mapEntityToResponse(entity);
		if (response == null) {
			return null;
		}
		if (response.getCompanyId() != null) {
			response.setCompany(companyService.getById(response.getCompanyId()));
		}
		if (response.getDepartmentId() != null) {
			response.setDepartment(departmentService.getById(response.getDepartmentId()));
		}
		return response;
	}
}
