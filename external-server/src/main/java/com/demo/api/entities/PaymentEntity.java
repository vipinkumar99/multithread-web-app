package com.demo.api.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class PaymentEntity extends BaseEntity {

	private String name;
	private Double amount;
	private String mode;

	public PaymentEntity() {
		super();
	}

	public PaymentEntity(String name, Double amount, String mode) {
		super();
		this.name = name;
		this.amount = amount;
		this.mode = mode;
	}

}
