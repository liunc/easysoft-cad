package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor; 
import com.easysoft.cad.domain.entity.OriginalProvince;

/**
 * 省、直辖市、自治区仓储接口
 * 
 * @author 刘年超
 */
public interface OriginalProvinceRepository extends JpaRepository<OriginalProvince, String>, JpaSpecificationExecutor<OriginalProvince> {

	public OriginalProvince findByCode(String code);
}
