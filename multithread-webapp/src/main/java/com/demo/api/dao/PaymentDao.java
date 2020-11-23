package com.demo.api.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.api.entities.PaymentEntity;

@Repository
public interface PaymentDao extends JpaRepository<PaymentEntity, Long> {
	@Transactional
	@Modifying
	@Query("update PaymentEntity p set p.exStatus=:status,p.exCode=:code where p.id=:id")
	void updateStatus(@Param("status") String status, @Param("code") String code, @Param("id") Long id);
}
