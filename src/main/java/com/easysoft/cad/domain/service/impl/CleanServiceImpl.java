package com.easysoft.cad.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import com.easysoft.cad.domain.entity.CleanPoolProvince;
import com.easysoft.cad.domain.entity.CleanRule;
import com.easysoft.cad.domain.entity.OriginalProvince;
import com.easysoft.cad.domain.service.CleanService;
import com.easysoft.cad.domain.valueObject.DataCategory;
import com.easysoft.cad.infrastructure.repository.CleanPoolCityRepository;
import com.easysoft.cad.infrastructure.repository.CleanPoolCountyRepository;
import com.easysoft.cad.infrastructure.repository.CleanPoolProvinceRepository;
import com.easysoft.cad.infrastructure.repository.CleanPoolTownRepository;
import com.easysoft.cad.infrastructure.repository.CleanPoolVillageRepository;
import com.easysoft.cad.infrastructure.repository.OriginalCityRepository;
import com.easysoft.cad.infrastructure.repository.OriginalCountyRepository;
import com.easysoft.cad.infrastructure.repository.OriginalProvinceRepository;
import com.easysoft.cad.infrastructure.repository.OriginalTownRepository;
import com.easysoft.cad.infrastructure.repository.OriginalVillageRepository;

public class CleanServiceImpl implements CleanService {

	@Autowired
	private OriginalProvinceRepository originalProvinceRepository;

	@Autowired
	private OriginalCityRepository originalCityRepository;

	@Autowired
	private OriginalCountyRepository originalCountyRepository;

	@Autowired
	private OriginalTownRepository originalTownRepository;

	@Autowired
	private OriginalVillageRepository originalVillageRepository;
	
	@Autowired
	private CleanPoolProvinceRepository cleanPoolProvinceRepository;
	
	@Autowired
	private CleanPoolCityRepository cleanPoolCityRepository;

	@Autowired
	private CleanPoolCountyRepository cleanPoolCountyRepository;

	@Autowired
	private CleanPoolTownRepository cleanPoolTownRepository;

	@Autowired
	private CleanPoolVillageRepository cleanPoolVillageRepository;
	
	@Override
	public void clean(List<CleanRule> rules) {

		this.clearCleanPool();
		this.importDataToCleanPool();
	}
	
	private void clearCleanPool() {
		this.cleanPoolProvinceRepository.deleteAllInBatch();
		this.cleanPoolCityRepository.deleteAllInBatch();
		this.cleanPoolCountyRepository.deleteAllInBatch();
		this.cleanPoolTownRepository.deleteAllInBatch();
		this.cleanPoolVillageRepository.deleteAllInBatch();
	}
	
	private void importDataToCleanPool() 
	{ 
		this.cleanPoolProvinceRepository.importData();
		this.cleanPoolCityRepository.importData();
		this.cleanPoolCountyRepository.importData();
		this.cleanPoolTownRepository.importData();
		this.cleanPoolVillageRepository.importData();
	}
	
	/*
	private void clean(List<CleanRule> rules) {
		List<CleanRule> provinceRules =  rules.stream().filter(i->i.getDataCategory().equals(DataCategory.PROVINCE)).collect(Collectors.<CleanRule>toList());
		
		List<CleanPoolProvince> provinces = new ArrayList<>();
		for(OriginalProvince original : this.originalProvinceRepository.findAll()) {
			CleanPoolProvince province = new CleanPoolProvince();
			province.create(original.getCode(), this.replace(original.getName(), provinceRules));
			provinces.add(province);
		}
		this.cleanPoolProvinceRepository.saveAll(provinces);
	}
	
	private String replace(String source, List<CleanRule> rules) {
		if(rules == null || rules.size() == 0) {
			return source;
		}
		for(CleanRule rule : rules) {
			if(source.contains( rule.getSearchText())) {
				return source.replace(rule.getSearchText(), rule.getReplaceText());
			}
		}
		return source;
	}
*/
}
