package com.demo.api.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

	private String name;
	private Long companyId;
	private String mobileNo;
	private String emailId;
	private Long departmentId;
	@Transient
	private String dep;
	@Transient
	private String com;

	public UserEntity() {
		super();
	}

	public UserEntity(String name, String com, String mobileNo, String emailId, String dep) {
		super();
		this.name = name;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.dep = dep;
		this.com = com;
	}

}
