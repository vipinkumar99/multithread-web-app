package com.demo.api.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.api.entities.EmailEntity;

public interface EmailDao extends JpaRepository<EmailEntity, Long> {
	@Transactional
	@Modifying
	@Query("update EmailEntity e set e.status=:status where e.id=:id")
	void updateStatus(@Param("status") String status, @Param("id") Long id);
}
