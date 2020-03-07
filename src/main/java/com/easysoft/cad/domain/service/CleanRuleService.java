package com.easysoft.cad.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.easysoft.cad.domain.entity.CleanRule;
import com.easysoft.core.util.EasysoftException;

public interface CleanRuleService {

	public Page<CleanRule> findAll(String dataCategory, Pageable pageable);
	
	public void add(String dataCategory, String searchText, String replaceText, int priority) throws EasysoftException;
	
	public void update(String id, String searchText, String replaceText, int priority) throws EasysoftException;
	
	public void delete(String id) throws EasysoftException;
	
	public CleanRule find(String id) throws EasysoftException;
}
                   