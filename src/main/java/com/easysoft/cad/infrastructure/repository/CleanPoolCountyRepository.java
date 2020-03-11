package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.cad.domain.entity.CleanPoolCounty;

/**
 * 区、县仓储接口
 * 
 * @author 刘年超
 */
public interface CleanPoolCountyRepository extends JpaRepository<CleanPoolCounty, String>, JpaSpecificationExecutor<CleanPoolCounty> {
 
	@Modifying
	@Transactional(rollbackFor = Exception.class)
	@Query("insert into CleanPoolCounty(code, name) select o.code, o.name from OriginalCounty o")
	public void importData();
}
