package com.easysoft.cad.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.easysoft.cad.domain.entity.CleanRule;
import com.easysoft.cad.domain.entity.DataAutoComplete;
import com.easysoft.cad.domain.entity.OriginalAll;
import com.easysoft.cad.domain.entity.OriginalCity;
import com.easysoft.cad.domain.entity.OriginalCounty;
import com.easysoft.cad.domain.entity.OriginalProvince;
import com.easysoft.cad.domain.entity.OriginalTown;
import com.easysoft.cad.domain.entity.OriginalVillage;
import com.easysoft.cad.domain.service.DataAutoCompleteService;
import com.easysoft.cad.infrastructure.repository.DataAutoCompleteRepository;
import com.easysoft.cad.infrastructure.repository.OriginalCityRepository;
import com.easysoft.cad.infrastructure.repository.OriginalCountyRepository;
import com.easysoft.cad.infrastructure.repository.OriginalProvinceRepository;
import com.easysoft.cad.infrastructure.repository.OriginalTownRepository;
import com.easysoft.cad.infrastructure.repository.OriginalVillageRepository;

public class DataAutoCompleteServiceImpl implements DataAutoCompleteService {

	@Autowired
	private DataAutoCompleteRepository dataAutoCompleteRepository;
	
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

	
	@Override
	public Page<DataAutoComplete> findAll(String name, Pageable pageable) {
		return this.dataAutoCompleteRepository.findAll(new Specification<DataAutoComplete>() {

			private static final long serialVersionUID = -3950053446063792958L;

			@Override
			public Predicate toPredicate(Root<DataAutoComplete> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();

				if (StringUtils.hasText(name)) {
					list.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%"));
				}

				if (list.size() > 0) {
					Predicate[] p = new Predicate[list.size()];
					return criteriaBuilder.and(list.toArray(p));
				}

				return null;
			}
		}, pageable);
	}

	@Override
	public void loadData() {
		 
		long count = this.dataAutoCompleteRepository.count();
		if (count != 0) {
			this.dataAutoCompleteRepository.deleteAll();
		}
		count = this.originalVillageRepository.count();

		int size = 1000;
		long page = count / size;
		if (count % size > 0) {
			page += 1;
		}
this.originalProvinceRepository.
		for (int i = 0; i < page; i++) {
			Page<OriginalVillage> villages = this.originalVillageRepository.findAll(PageRequest.of(i, size));
			List<DataAutoComplete> entities = new ArrayList<DataAutoComplete>();
			OriginalProvince province = new OriginalProvince();
			OriginalCity city = new OriginalCity();
			OriginalCounty county = new OriginalCounty();
			OriginalTown town = new OriginalTown();
			for (OriginalVillage village : villages.getContent()) {
				DataAutoComplete entity = new DataAutoComplete();
				entity.create(village.getCode(), village.getCategory(), village.getName());

				if (!entity.getProvinceCode().equals(province.getCode())) {
					OriginalProvince provinceEntity = this.originalProvinceRepository
							.findByCode(entity.getProvinceCode());
					if (provinceEntity == null) {
						province.create(entity.getProvinceCode(), "");
					} else {
						province = provinceEntity;
					}
				}
				entity.setProvinceName(province.getName());

				if (!entity.getCityCode().equals(city.getCode())) {
					OriginalCity cityEntity = this.originalCityRepository.findByCode(entity.getCityCode());
					if (cityEntity == null) {
						city.create(entity.getCityCode(), "");
					} else {
						city = cityEntity;
					}
				}
				entity.setCityName(city.getName());

				if (!entity.getCountyCode().equals(county.getCode())) {
					OriginalCounty countyEntity = this.originalCountyRepository.findByCode(entity.getCountyCode());
					if (countyEntity == null) {
						county.create(entity.getCountyCode(), "");
					} else {
						county = countyEntity;
					}
				}
				entity.setCountyName(county.getName());

				if (!entity.getTownCode().equals(town.getCode())) {
					OriginalTown townEntity = this.originalTownRepository.findByCode(entity.getTownCode());
					if (townEntity == null) {
						town.create(entity.getTownCode(), "");
					} else {
						town = townEntity;
					}
				}
				entity.setTownName(town.getName());

				entities.add(entity);
			}
			this.originalAllRepository.saveAll(entities);
		}
		
	}

	@Override
	public void saveSchemeRule(List<CleanRule> rules) {
		// TODO Auto-generated method stub
		
	}

}
