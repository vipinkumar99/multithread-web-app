package com.demo.api.threads;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.demo.api.dao.UserDao;
import com.demo.api.entities.UserEntity;

public class InsertUserThread extends AbstractRunnableThread {

	@Autowired
	private UserDao userDao;

	private Set<UserEntity> users;

	public InsertUserThread(Set<UserEntity> users) {
		this.users = users;
	}

	@Override
	public void preformTask() {
		if (!CollectionUtils.isEmpty(users)) {
			userDao.saveAll(users);
		}
	}

	@Override
	public int noOfRetries() {
		return 1;
	}

}
