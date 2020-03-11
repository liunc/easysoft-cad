package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.easysoft.cad.domain.entity.OriginalVillage;

/**
 * 街道、村仓储接口
 * 
 * @author 刘年超
 */
public interface OriginalVillageRepository extends JpaRepository<OriginalVillage, String>, JpaSpecificationExecutor<OriginalVillage> {

	public OriginalVillage findByCode(String code);
}
