package com.easysoft.cad.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.easysoft.cad.domain.entity.OriginalCity;
import com.easysoft.cad.domain.entity.OriginalCounty;
import com.easysoft.cad.domain.entity.OriginalProvince;
import com.easysoft.cad.domain.entity.OriginalTown;
import com.easysoft.cad.domain.entity.OriginalVillage;
import com.easysoft.core.util.EasysoftException;

public interface OriginalDataService {

	public Page<OriginalProvince> findProvinces(String code, String name, Pageable pageable);

	public Page<OriginalCity> findCities(String provinceCode, String code, String name, Pageable pageable);

	public Page<OriginalCounty> findCounties(String cityCode, String code, String name, Pageable pageable);

	public Page<OriginalTown> findTowns(String countyCode, String code, String name, Pageable pageable);

	public Page<OriginalVillage> findVillages(String townCode, String code, String name, Pageable pageable);
	
	public OriginalProvince findProvince(String code) throws EasysoftException;
	
	public OriginalCity findCity(String code) throws EasysoftException;
	
	public OriginalCounty findCounty(String code) throws EasysoftException;
	
	public OriginalTown findTown(String code) throws EasysoftException;
	
	public OriginalVillage findVillage(String code) throws EasysoftException;
	
	public void updateProvince(String code, String name) throws EasysoftException;
	
	public void updateCity(String code, String name) throws EasysoftException;
	
	public void updateCounty(String code, String name) throws EasysoftException;
	
	public void updateTown(String code, String name) throws EasysoftException;
	
	public void updateVillage(String code, String name) throws EasysoftException;
}
