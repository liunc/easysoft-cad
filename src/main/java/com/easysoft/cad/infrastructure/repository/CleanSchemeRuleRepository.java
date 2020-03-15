package com.easysoft.cad.infrastructure.repository;

import java.util.List;

import com.easysoft.cad.domain.entity.CleanSchemeRule;
import com.easysoft.core.data.jpa.repository.BaseRepository;

public interface CleanSchemeRuleRepository extends BaseRepository<CleanSchemeRule, String> {

	public List<CleanSchemeRule> findBySchemeId(String schemeId);
	
	public List<CleanSchemeRule> findByRuleId(String ruleId);
	
	public void deleteByRuleId(String ruleId);
	
}
