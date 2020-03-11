package com.easysoft.cad.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.easysoft.cad.domain.entity.CleanSchemeRule;

public interface CleanSchemeRuleRepository extends JpaRepository<CleanSchemeRule, String>, JpaSpecificationExecutor<CleanSchemeRule> {

	public List<CleanSchemeRule> findBySchemeId(String schemeId);
	
	public List<CleanSchemeRule> findByRuleId(String ruleId);
	
	public void deleteByRuleId(String ruleId);
	
}
