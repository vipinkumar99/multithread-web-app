package com.demo.api.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sendEmails")
public class EmailEntity extends BaseEntity {
	private String emailId;
	private String msg;
	private String status;
}
