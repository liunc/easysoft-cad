package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.easysoft.cad.domain.entity.OriginalCounty;

/**
 * 区、县仓储接口
 * 
 * @author 刘年超
 */
public interface OriginalCountyRepository extends PagingAndSortingRepository<OriginalCounty, String>, JpaSpecificationExecutor<OriginalCounty> {

	public OriginalCounty findByCode(String code);
}
