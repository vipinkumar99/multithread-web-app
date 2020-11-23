package com.demo.api.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "paymentCollections")
public class PaymentEntity extends BaseEntity {
	private Double amount;
	private String name;
	private String mode;
	private String exStatus;
	private String exCode;
}
