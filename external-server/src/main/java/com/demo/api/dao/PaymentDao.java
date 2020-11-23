package com.demo.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.api.entities.PaymentEntity;

@Repository
public interface PaymentDao extends JpaRepository<PaymentEntity, Long> {
}
