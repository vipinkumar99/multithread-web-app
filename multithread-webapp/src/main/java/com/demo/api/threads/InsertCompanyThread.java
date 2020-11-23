package com.demo.api.threads;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.demo.api.dao.CompanyDao;
import com.demo.api.entities.CompanyEntity;

public class InsertCompanyThread extends AbstractRunnableThread {

	@Autowired
	private CompanyDao companyDao;

	private Set<String> companies;

	public InsertCompanyThread(Set<String> companies) {
		this.companies = companies;
	}

	@Override
	public void preformTask() {
		if (!CollectionUtils.isEmpty(companies)) {
			for (String name : companies) {
				if (!companyDao.findByName(name).isPresent()) {
					companyDao.save(new CompanyEntity(name));
				}
			}
		}
	}

	@Override
	public int noOfRetries() {
		return 1;
	}

}
