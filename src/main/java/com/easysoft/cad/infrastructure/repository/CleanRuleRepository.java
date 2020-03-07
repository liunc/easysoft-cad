package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.easysoft.cad.domain.entity.CleanRule;

/**
 * 清洗规则仓储接口
 * 
 * @author 刘年超
 */
public interface CleanRuleRepository extends PagingAndSortingRepository<CleanRule, String>, JpaSpecificationExecutor<CleanRule> {
	
	public boolean existsByDataCategoryAndSearchText(String dataCategory, String searchText);

}
