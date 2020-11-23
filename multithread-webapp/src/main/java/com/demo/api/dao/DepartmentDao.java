package com.demo.api.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.api.entities.DepartmentEntity;

@Repository
public interface DepartmentDao extends JpaRepository<DepartmentEntity, Long> {
	Optional<DepartmentEntity>findByName(String name);
}
