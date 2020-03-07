package com.easysoft.cad.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.easysoft.cad.domain.entity.CleanRule;
import com.easysoft.cad.domain.entity.DataAutoComplete;

public interface DataAutoCompleteService {

	public Page<DataAutoComplete> findAll(String name, Pageable pageable);

	public void loadData();
	
	public void saveSchemeRule(List<CleanRule> rules);
}
                   