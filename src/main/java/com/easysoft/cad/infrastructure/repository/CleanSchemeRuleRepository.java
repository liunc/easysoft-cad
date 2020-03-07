package com.easysoft.cad.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.easysoft.cad.domain.entity.CleanSchemeRule;

public interface CleanSchemeRuleRepository extends PagingAndSortingRepository<CleanSchemeRule, String>, JpaSpecificationExecutor<CleanSchemeRule> {

	public List<CleanSchemeRule> findBySchemeId(String schemeId);
	
}
