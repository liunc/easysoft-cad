package com.easysoft.cad.infrastructure.repository;

import com.easysoft.cad.domain.entity.CleanRule;
import com.easysoft.core.data.jpa.repository.BaseRepository;

/**
 * 清洗规则仓储接口
 * 
 * @author 刘年超
 */
public interface CleanRuleRepository extends BaseRepository<CleanRule, String> {
	
	public boolean existsByDataCategoryAndSearchText(String dataCategory, String searchText);

}
