package com.demo.api.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.api.entities.CompanyEntity;

@Repository
public interface CompanyDao extends JpaRepository<CompanyEntity, Long> {
Optional<CompanyEntity>findByName(String name);
}
