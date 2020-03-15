package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.cad.domain.entity.CleanPoolVillage;
import com.easysoft.core.data.jpa.repository.BaseRepository;

/**
 * 街道、村仓储接口
 * 
 * @author 刘年超
 */
public interface CleanPoolVillageRepository extends BaseRepository<CleanPoolVillage, String> {

	@Modifying
	@Transactional(rollbackFor = Exception.class)
	@Query("insert into CleanPoolVillage(code, category, name) select o.code, o.category, o.name from OriginalVillage o")
	public void importData();
}
