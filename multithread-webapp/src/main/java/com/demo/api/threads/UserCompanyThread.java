package com.demo.api.threads;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.api.dao.CompanyDao;
import com.demo.api.dao.UserDao;
import com.demo.api.entities.CompanyEntity;
import com.demo.api.entities.UserEntity;

public class UserCompanyThread extends AbstractRunnableThread {

	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private UserDao userDao;

	private List<UserEntity> users;

	public UserCompanyThread(List<UserEntity> users) {
		super();
		this.users = users;
	}

	@Override
	public void preformTask() {
		Map<String, Long> cmap = new HashMap<>();
		for (UserEntity user : users) {
			if (user.getCom() != null && !user.getCom().equals("")) {
				if (cmap.containsKey(user.getCom())) {
					userDao.updateCompanyId(cmap.get(user.getCom()), user.getId());
				} else {
					Optional<CompanyEntity> cOptional = companyDao.findByName(user.getCom());
					if (cOptional.isPresent()) {
						cmap.put(user.getCom(), cOptional.get().getId());
						userDao.updateCompanyId(cmap.get(user.getCom()), user.getId());
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
