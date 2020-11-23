package com.demo.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class DepartmentEntity extends BaseEntity {

	@Column(unique = true)
	private String name;

	public DepartmentEntity() {
		super();
	}

	public DepartmentEntity(String name) {
		super();
		this.name = name;
	}

}
