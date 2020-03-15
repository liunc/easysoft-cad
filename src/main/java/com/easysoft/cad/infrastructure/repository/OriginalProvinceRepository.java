package com.easysoft.cad.infrastructure.repository;

import com.easysoft.cad.domain.entity.OriginalProvince;
import com.easysoft.core.data.jpa.repository.BaseRepository;

/**
 * 省、直辖市、自治区仓储接口
 * 
 * @author 刘年超
 */
public interface OriginalProvinceRepository extends BaseRepository<OriginalProvince, String>{

	public OriginalProvince findByCode(String code);
}
