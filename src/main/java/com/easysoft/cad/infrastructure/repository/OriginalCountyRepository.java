package com.easysoft.cad.infrastructure.repository;

import com.easysoft.cad.domain.entity.OriginalCounty;
import com.easysoft.core.data.jpa.repository.BaseRepository;

/**
 * 区、县仓储接口
 * 
 * @author 刘年超
 */
public interface OriginalCountyRepository extends BaseRepository<OriginalCounty, String> {

	public OriginalCounty findByCode(String code);
}
