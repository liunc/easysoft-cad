package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.cad.domain.entity.CleanPoolProvince;

/**
 * 省、直辖市、自治区仓储接口
 * 
 * @author 刘年超
 */
public interface CleanPoolProvinceRepository extends JpaRepository<CleanPoolProvince, String>, JpaSpecificationExecutor<CleanPoolProvince> {

	@Modifying
	@Transactional(rollbackFor = Exception.class)
	@Query("insert into CleanPoolProvince(code, name) select o.code, o.name from OriginalProvince o")
	public void importData();
}
