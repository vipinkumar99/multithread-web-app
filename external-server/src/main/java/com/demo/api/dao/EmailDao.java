package com.demo.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.api.entities.EmailEntity;

@Repository
public interface EmailDao extends JpaRepository<EmailEntity, Long> {

}
