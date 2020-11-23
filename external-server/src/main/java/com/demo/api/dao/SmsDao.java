package com.demo.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.api.entities.SmsEntity;

@Repository
public interface SmsDao extends JpaRepository<SmsEntity, Long> {
}
