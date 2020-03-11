package com.easysoft.cad.domain.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.easysoft.cad.domain.entity.OriginalAll;
import com.easysoft.cad.domain.entity.OriginalCity;
import com.easysoft.cad.domain.entity.OriginalCounty;
import com.easysoft.cad.domain.entity.OriginalProvince;
import com.easysoft.cad.domain.entity.OriginalTown;
import com.easysoft.cad.domain.entity.OriginalVillage;
import com.easysoft.cad.domain.service.OriginalDataService;
import com.easysoft.cad.infrastructure.repository.OriginalAllRepository;
import com.easysoft.cad.infrastructure.repository.OriginalCityRepository;
import com.easysoft.cad.infrastructure.repository.OriginalCountyRepository;
import com.easysoft.cad.infrastructure.repository.OriginalProvinceRepository;
import com.easysoft.cad.infrastructure.repository.OriginalTownRepository;
import com.easysoft.cad.infrastructure.repository.OriginalVillageRepository;
import com.easysoft.core.util.EasysoftException;
import com.easysoft.core.util.EasysoftMessageSource;

@Service
public class OriginalDataServiceImpl implements OriginalDataService {

	private static final Logger logger = LoggerFactory.getLogger(OriginalDataServiceImpl.class);

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
	private OriginalAllRepository originalAllRepository;

	@Autowired
	private EasysoftMessageSource messageSource;

	@Override
	public Page<OriginalProvince> findProvinces(String code, String name, Pageable pageable) {

		return this.originalProvinceRepository.findAll(new Specification<OriginalProvince>() {

			private static final long serialVersionUID = -3950053446063792958L;

			@Override
			public Predicate toPredicate(Root<OriginalProvince> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();

				if (StringUtils.hasText(code)) {
					list.add(criteriaBuilder.like(root.get("code").as(String.class), "%" + code + "%"));
				}

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
	public Page<OriginalCity> findCities(String provinceCode, String code, String name, Pageable pageable) {
		return this.originalCityRepository.findAll(new Specification<OriginalCity>() {

			private static final long serialVersionUID = -3950053446063792958L;

			@Override
			public Predicate toPredicate(Root<OriginalCity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();

				if (StringUtils.hasText(code)) {
					list.add(criteriaBuilder.like(root.get("code").as(String.class), "%" + code + "%"));
				}

				if (StringUtils.hasText(provinceCode)) {
					list.add(criteriaBuilder.like(root.get("code").as(String.class), provinceCode + "%"));
				}

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
	public Page<OriginalCounty> findCounties(String cityCode, String code, String name, Pageable pageable) {
		return this.originalCountyRepository.findAll(new Specification<OriginalCounty>() {

			private static final long serialVersionUID = -3950053446063792958L;

			@Override
			public Predicate toPredicate(Root<OriginalCounty> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();

				if (StringUtils.hasText(code)) {
					list.add(criteriaBuilder.like(root.get("code").as(String.class), "%" + code + "%"));
				}

				if (StringUtils.hasText(cityCode)) {
					String queryCode = cityCode;
					if (queryCode.length() > 4) {
						queryCode = queryCode.substring(0, 4);
					}
					list.add(criteriaBuilder.like(root.get("code").as(String.class), queryCode + "%"));
				}

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
	public Page<OriginalTown> findTowns(String countyCode, String code, String name, Pageable pageable) {
		return this.originalTownRepository.findAll(new Specification<OriginalTown>() {

			private static final long serialVersionUID = -3950053446063792958L;

			@Override
			public Predicate toPredicate(Root<OriginalTown> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();

				if (StringUtils.hasText(code)) {
					list.add(criteriaBuilder.like(root.get("code").as(String.class), "%" + code + "%"));
				}

				if (StringUtils.hasText(countyCode)) {
					String queryCode = countyCode;
					if (queryCode.length() > 6) {
						queryCode = queryCode.substring(0, 6);
					}
					list.add(criteriaBuilder.like(root.get("code").as(String.class), queryCode + "%"));
				}

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
	public Page<OriginalVillage> findVillages(String townCode, String code, String name, Pageable pageable) {
		return this.originalVillageRepository.findAll(new Specification<OriginalVillage>() {

			private static final long serialVersionUID = -3950053446063792958L;

			@Override
			public Predicate toPredicate(Root<OriginalVillage> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();

				if (StringUtils.hasText(code)) {
					list.add(criteriaBuilder.like(root.get("code").as(String.class), "%" + code + "%"));
				}

				if (StringUtils.hasText(townCode)) {
					String queryCode = townCode;
					if (queryCode.length() > 9) {
						queryCode = queryCode.substring(0, 9);
					}
					list.add(criteriaBuilder.like(root.get("code").as(String.class), queryCode + "%"));
				}

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
	public OriginalProvince findProvince(String code) throws EasysoftException {
		OriginalProvince entity = this.originalProvinceRepository.findByCode(code);
		if (entity == null) {
			throw new EasysoftException(this.messageSource.getMessage("data_not_found", new Object[] {
					this.messageSource.getMessage("province"), this.messageSource.getMessage("code"), code }));
		}
		return entity;
	}

	@Override
	public OriginalCity findCity(String code) throws EasysoftException {
		OriginalCity entity = this.originalCityRepository.findByCode(code);
		if (entity == null) {
			throw new EasysoftException(this.messageSource.getMessage("data_not_found", new Object[] {
					this.messageSource.getMessage("city"), this.messageSource.getMessage("code"), code }));
		}
		return entity;
	}

	@Override
	public OriginalCounty findCounty(String code) throws EasysoftException {
		OriginalCounty entity = this.originalCountyRepository.findByCode(code);
		if (entity == null) {
			throw new EasysoftException(this.messageSource.getMessage("data_not_found", new Object[] {
					this.messageSource.getMessage("county"), this.messageSource.getMessage("code"), code }));
		}
		return entity;
	}

	@Override
	public OriginalTown findTown(String code) throws EasysoftException {
		OriginalTown entity = this.originalTownRepository.findByCode(code);
		if (entity == null) {
			throw new EasysoftException(this.messageSource.getMessage("data_not_found", new Object[] {
					this.messageSource.getMessage("town"), this.messageSource.getMessage("code"), code }));
		}
		return entity;
	}

	@Override
	public OriginalVillage findVillage(String code) throws EasysoftException {
		OriginalVillage entity = this.originalVillageRepository.findByCode(code);
		if (entity == null) {
			throw new EasysoftException(this.messageSource.getMessage("data_not_found", new Object[] {
					this.messageSource.getMessage("village"), this.messageSource.getMessage("code"), code }));
		}
		return entity;
	}

	@Override
	public void updateProvince(String code, String name) throws EasysoftException {

		OriginalProvince entity = this.findProvince(code);
		entity.updateName(name);
		this.originalProvinceRepository.save(entity);
	}

	@Override
	public void updateCity(String code, String name) throws EasysoftException {

		OriginalCity entity = this.findCity(code);
		entity.updateName(name);
		this.originalCityRepository.save(entity);
	}

	@Override
	public void updateCounty(String code, String name) throws EasysoftException {

		OriginalCounty entity = this.findCounty(code);
		entity.updateName(name);
		this.originalCountyRepository.save(entity);
	}

	@Override
	public void updateTown(String code, String name) throws EasysoftException {

		OriginalTown entity = this.findTown(code);
		entity.updateName(name);
		this.originalTownRepository.save(entity);
	}

	@Override
	public void updateVillage(String code, String name) throws EasysoftException {

		OriginalVillage entity = this.findVillage(code);
		entity.updateName(name);
		this.originalVillageRepository.save(entity);
	}

	@Async("taskExecutor")
	@Override
	public void importToAll() {
		long count = this.originalAllRepository.count();
		if (count != 0) {
			this.originalAllRepository.deleteAllInBatch();
		}
		count = this.originalVillageRepository.count();

		int size = 1000;
		long page = count / size;
		if (count % size > 0) {
			page += 1;
		}

		for (int i = 0; i < page; i++) {
			Page<OriginalVillage> villages = this.originalVillageRepository.findAll(PageRequest.of(i, size));
			List<OriginalAll> entities = new ArrayList<OriginalAll>();
			OriginalProvince province = new OriginalProvince();
			OriginalCity city = new OriginalCity();
			OriginalCounty county = new OriginalCounty();
			OriginalTown town = new OriginalTown();
			for (OriginalVillage village : villages.getContent()) {
				OriginalAll entity = new OriginalAll();
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
	public Page<OriginalAll> findAll(String provinceName, String cityName, String countyName, String townName,
			String villageName, Pageable pageable) {
		return this.originalAllRepository.findAll(new Specification<OriginalAll>() {

			private static final long serialVersionUID = -3950053446063792958L;

			@Override
			public Predicate toPredicate(Root<OriginalAll> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();

				if (StringUtils.hasText(provinceName)) {
					list.add(criteriaBuilder.like(root.get("provinceName").as(String.class), "%" + provinceName + "%"));
				}

				if (StringUtils.hasText(cityName)) {
					list.add(criteriaBuilder.like(root.get("cityName").as(String.class), "%" + cityName + "%"));
				}

				if (StringUtils.hasText(countyName)) {
					list.add(criteriaBuilder.like(root.get("countyName").as(String.class), "%" + countyName + "%"));
				}

				if (StringUtils.hasText(townName)) {
					list.add(criteriaBuilder.like(root.get("townName").as(String.class), "%" + townName + "%"));
				}

				if (StringUtils.hasText(villageName)) {
					list.add(criteriaBuilder.like(root.get("villageName").as(String.class), "%" + villageName + "%"));
				}

				if (list.size() > 0) {
					Predicate[] p = new Predicate[list.size()];
					return criteriaBuilder.and(list.toArray(p));
				}

				return null;
			}
		}, pageable);
	}

	//@Async("taskExecutor")
	@Override
	public void exportAll(String exportPath) {

		try {
			logger.info(exportPath);
			// HSSFWorkbook wb1 = new HSSFWorkbook();
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheets = workbook.createSheet("九九乘法表");
			for (int i = 1; i <= 9; i++) {
				XSSFRow row = sheets.createRow(i - 1);
				for (int j = 1; j <= 9; j++) {
					XSSFCell cell = row.createCell(j - 1);
					cell.setCellValue(i + "*" + j + "=" + i * j);
				}

			}
			exportPath += "\\export";
			File file = new File(exportPath);
			if(!file.exists()) {
				file.mkdirs();
			}
			FileOutputStream fileOutputStream = new FileOutputStream(exportPath +"\\test2.xlsx");

			workbook.write(fileOutputStream);

		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
	}
}
