package com.demo.api.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sms")
public class SmsEntity extends BaseEntity {
	private String mobileNo;
	private String message;

	public SmsEntity() {
		super();
	}

	public SmsEntity(String mobileNo, String message) {
		super();
		this.mobileNo = mobileNo;
		this.message = message;
	}

}
