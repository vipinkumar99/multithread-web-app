package com.demo.api.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "emails")
public class EmailEntity extends BaseEntity {

	private String emailId;
	private String message;

	public EmailEntity() {
		super();
	}

	public EmailEntity(String emailId, String message) {
		super();
		this.emailId = emailId;
		this.message = message;
	}

}
