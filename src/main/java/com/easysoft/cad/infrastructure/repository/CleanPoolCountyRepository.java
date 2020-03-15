package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.cad.domain.entity.CleanPoolCounty;
import com.easysoft.core.data.jpa.repository.BaseRepository;

/**
 * 区、县仓储接口
 * 
 * @author 刘年超
 */
public interface CleanPoolCountyRepository extends BaseRepository<CleanPoolCounty, String> {
 
	@Modifying
	@Transactional(rollbackFor = Exception.class)
	@Query("insert into CleanPoolCounty(code, name) select o.code, o.name from OriginalCounty o")
	public void importData();
}
