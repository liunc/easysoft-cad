package com.easysoft.cad.infrastructure.repository;

import com.easysoft.cad.domain.entity.OriginalCity;
import com.easysoft.core.data.jpa.repository.BaseRepository;

/**
 * 地级市、省、自治区直辖县级行政区划仓储接口
 * 
 * @author 刘年超
 */
public interface OriginalCityRepository extends BaseRepository<OriginalCity, String> {

	public OriginalCity findByCode(String code);
}
