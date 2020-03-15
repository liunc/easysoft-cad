package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.cad.domain.entity.CleanPoolProvince;
import com.easysoft.core.data.jpa.repository.BaseRepository;

/**
 * 省、直辖市、自治区仓储接口
 * 
 * @author 刘年超
 */
public interface CleanPoolProvinceRepository extends BaseRepository<CleanPoolProvince, String> {

	@Modifying
	@Transactional(rollbackFor = Exception.class)
	@Query("insert into CleanPoolProvince(code, name) select o.code, o.name from OriginalProvince o")
	public void importData();
}
