package com.demo.api.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.api.entities.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {
	@Transactional
	@Modifying
	@Query("update UserEntity u set u.companyId=:companyId where u.id=:id")
	void updateCompanyId(@Param("companyId") Long companyId, @Param("id") Long id);
	@Transactional
	@Modifying
	@Query("update UserEntity u set u.departmentId=:departmentId where u.id=:id")
	void updateDepartmentId(@Param("departmentId") Long departmentId, @Param("id") Long id);
}
