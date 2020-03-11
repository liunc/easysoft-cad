package com.easysoft.cad.domain.service;

import java.util.List;

import com.easysoft.cad.domain.entity.CleanRule;

public interface CleanService {

	public void clean(List<CleanRule> rules);
}
