package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.cad.domain.entity.CleanPoolCity;

/**
 * 地级市、省、自治区直辖县级行政区划仓储接口
 * 
 * @author 刘年超
 */
public interface CleanPoolCityRepository extends JpaRepository<CleanPoolCity, String>, JpaSpecificationExecutor<CleanPoolCity> {
 
	@Modifying
	@Transactional(rollbackFor = Exception.class)
	@Query("insert into CleanPoolCity(code, name) select o.code, o.name from OriginalCity o")
	public void importData();
}
