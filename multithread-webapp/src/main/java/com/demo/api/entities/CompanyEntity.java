package com.demo.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "companies")
public class CompanyEntity extends BaseEntity {
	@Column(unique = true)
	private String name;

	public CompanyEntity() {
		super();
	}

	public CompanyEntity(String name) {
		super();
		this.name = name;
	}

}
