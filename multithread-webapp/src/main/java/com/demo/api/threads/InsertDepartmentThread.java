package com.demo.api.threads;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.demo.api.dao.DepartmentDao;
import com.demo.api.entities.DepartmentEntity;

public class InsertDepartmentThread extends AbstractRunnableThread {

	@Autowired
	private DepartmentDao departmentDao;

	private Set<String> departments;

	public InsertDepartmentThread(Set<String> departments) {
		this.departments = departments;
	}

	@Override
	public void preformTask() {
		if (!CollectionUtils.isEmpty(departments)) {
			for (String name : departments) {
				if (!departmentDao.findByName(name).isPresent()) {
					departmentDao.save(new DepartmentEntity(name));
				}
			}
		}
	}

	@Override
	public int noOfRetries() {
		return 1;
	}

}
