package com.demo.api.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sendSms")
public class SmsEntity extends BaseEntity {
	private String mobileNo;
	private String msg;
	private String status;
}
