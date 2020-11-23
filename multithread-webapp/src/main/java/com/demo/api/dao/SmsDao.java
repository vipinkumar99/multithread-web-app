package com.demo.api.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.api.entities.SmsEntity;

@Repository
public interface SmsDao extends JpaRepository<SmsEntity, Long> {
	@Transactional
	@Modifying
	@Query("update SmsEntity s set s.status=:status where s.id=:id")
	void updateStatus(@Param("status") String status, @Param("id") Long id);
}
