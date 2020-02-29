package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.easysoft.cad.domain.entity.OriginalCity;

/**
 * 地级市、省、自治区直辖县级行政区划仓储接口
 * 
 * @author 刘年超
 */
public interface OriginalCityRepository extends PagingAndSortingRepository<OriginalCity, String>, JpaSpecificationExecutor<OriginalCity> {

	public OriginalCity findByCode(String code);
}
