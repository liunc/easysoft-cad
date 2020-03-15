package com.easysoft.cad.infrastructure.repository;

import com.easysoft.cad.domain.entity.OriginalVillage;
import com.easysoft.core.data.jpa.repository.BaseRepository;

/**
 * 街道、村仓储接口
 * 
 * @author 刘年超
 */
public interface OriginalVillageRepository extends BaseRepository<OriginalVillage, String> {

	public OriginalVillage findByCode(String code);
}
