package com.demo.api.threads;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.api.dao.DepartmentDao;
import com.demo.api.dao.UserDao;
import com.demo.api.entities.DepartmentEntity;
import com.demo.api.entities.UserEntity;

public class UserDepartmentThread extends AbstractRunnableThread {

	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private UserDao userDao;

	private List<UserEntity> users;

	public UserDepartmentThread(List<UserEntity> users) {
		super();
		this.users = users;
	}

	@Override
	public void preformTask() {
		Map<String, Long> dmap = new HashMap<>();
		for (UserEntity user : users) {
			if (user.getDep() != null && !user.getDep().equals("")) {
				if (dmap.containsKey(user.getDep())) {
					userDao.updateDepartmentId(dmap.get(user.getDep()), user.getId());
				} else {
					Optional<DepartmentEntity> dOptional = departmentDao.findByName(user.getDep());
					if (dOptional.isPresent()) {
						dmap.put(user.getDep(), dOptional.get().getId());
						userDao.updateDepartmentId(dmap.get(user.getDep()), user.getId());
					}
				}
			}
		}
	}

	@Override
	public int noOfRetries() {
		return 1;
	}

}
