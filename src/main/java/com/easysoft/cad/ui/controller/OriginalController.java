package com.easysoft.cad.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easysoft.cad.domain.entity.OriginalAll;
import com.easysoft.cad.domain.entity.OriginalCity;
import com.easysoft.cad.domain.entity.OriginalCounty;
import com.easysoft.cad.domain.entity.OriginalProvince;
import com.easysoft.cad.domain.entity.OriginalTown;
import com.easysoft.cad.domain.entity.OriginalVillage;
import com.easysoft.cad.domain.service.CollectService;
import com.easysoft.cad.domain.service.OriginalService;
import com.easysoft.cad.ui.viewModel.original.*;
import com.easysoft.core.util.EasysoftException;
import com.easysoft.core.util.EasysoftMessageSource;
import com.easysoft.core.web.viewModel.AjaxResponse;
import com.easysoft.core.web.viewModel.BootstrapTableResponse;

@Controller
@RequestMapping("/original")
public class OriginalController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(OriginalController.class);

	@Autowired
	private OriginalService originalService;

	@Autowired
	private CollectService collectService;

	@Autowired
	private EasysoftMessageSource messageSource;

	@RequestMapping("/index")
	public String index(Model model, HttpServletRequest request) {
		
		super.getLayoutData(model, request);
		
		return "original/index";
	}

	@RequestMapping(value = "/provinces", method = RequestMethod.POST)
	@ResponseBody
	public BootstrapTableResponse<OriginalProvince> getProvinces(@RequestBody OriginalProvincePageRequest request) {

		BootstrapTableResponse<OriginalProvince> response = new BootstrapTableResponse<OriginalProvince>();

		Pageable pageable = PageRequest.of(request.getPage(), request.getLimit());
		if (StringUtils.hasText(request.getSort())) {
			pageable = PageRequest.of(request.getPage(), request.getLimit(),
					request.isDesc() ? Direction.DESC : Direction.ASC, request.getSort());
		}

		Page<OriginalProvince> entities = this.originalService.findProvinces(request.getCode(), request.getName(),
				pageable);
		if (entities == null || entities.getTotalElements() == 0) {
			return response;
		}
		response.setRows(entities.getContent());
		response.setTotal(entities.getTotalElements());

		return response;
	}

	@RequestMapping("/province_{code}")
	public String provinceEdit(Model model, @PathVariable String code) {
		try {
			OriginalProvince entity = this.originalService.findProvince(code);
			model.addAttribute("vm", entity);
			return "original/province_edit";
		} catch (EasysoftException e) {
			logger.error(e.toString());
			model.addAttribute("error", e.getMessage());
			return "common/tab_error";
		}
	}

	@RequestMapping(value = "/province/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse provinceEdit(@RequestBody @Valid OriginalProvinceEditRequest request,
			BindingResult validatedResult) {

		AjaxResponse response = new AjaxResponse(validatedResult);

		if (response.isResult()) {
			try {
				this.originalService.updateProvince(request.getCode(), request.getName());
				response.setMessage(this.messageSource.getMessage("edit_success"));

			} catch (EasysoftException ex) {
				response.setResult(false);
				response.setMessage(this.messageSource.getMessage("edit_failed", new Object[] { ex.getMessage() }));
			} catch (Exception ex) {
				logger.error(ex.toString());
				response.setResult(false);
				response.setMessage(this.messageSource.getMessage("edit_failed", new Object[] { ex.getMessage() }));
			}
		}
		return response;
	}

	@RequestMapping("/city")
	public String city(Model model, @RequestParam("province") String provinceCode) {
		OriginalProvince province = null;
		String collectUrl = "";
		try {
			province = this.originalService.findProvince(provinceCode);
			collectUrl = this.collectService.getUrl(provinceCode);
		} catch (EasysoftException e) {
			logger.error(e.toString());
			model.addAttribute("error", e.getMessage());
			return "common/tab_error";
		}
		model.addAttribute("province", province);
		model.addAttribute("collectUrl", collectUrl);
		return "original/city";
	}

	@RequestMapping(value = "/cities", method = RequestMethod.POST)
	@ResponseBody
	public BootstrapTableResponse<OriginalCity> getCities(@RequestBody OriginalCityPageRequest request) {

		BootstrapTableResponse<OriginalCity> response = new BootstrapTableResponse<OriginalCity>();

		Pageable pageable = PageRequest.of(request.getPage(), request.getLimit());
		if (StringUtils.hasText(request.getSort())) {
			pageable = PageRequest.of(request.getPage(), request.getLimit(),
					request.isDesc() ? Direction.DESC : Direction.ASC, request.getSort());
		}

		Page<OriginalCity> entities = this.originalService.findCities(request.getProvinceCode(), request.getCode(),
				request.getName(), pageable);
		if (entities == null || entities.getTotalElements() == 0) {
			return response;
		}
		response.setRows(entities.getContent());
		response.setTotal(entities.getTotalElements());

		return response;
	}

	@RequestMapping("/city_{code}")
	public String cityEdit(Model model, @PathVariable String code) {
		try {
			OriginalCity entity = this.originalService.findCity(code);
			model.addAttribute("vm", entity);
			return "original/city_edit";
		} catch (EasysoftException e) {
			logger.error(e.toString());
			model.addAttribute("error", e.getMessage());
			return "common/tab_error";
		}
	}

	@RequestMapping(value = "/city/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse cityEdit(@RequestBody @Valid OriginalCityEditRequest request, BindingResult validatedResult) {

		AjaxResponse response = new AjaxResponse(validatedResult);

		if (response.isResult()) {
			try {
				this.originalService.updateCity(request.getCode(), request.getName());
				response.setMessage(this.messageSource.getMessage("edit_success"));

			} catch (EasysoftException ex) {
				response.setResult(false);
				response.setMessage(this.messageSource.getMessage("edit_failed", new Object[] { ex.getMessage() }));
			} catch (Exception ex) {
				logger.error(ex.toString());
				response.setResult(false);
				response.setMessage(this.messageSource.getMessage("edit_failed", new Object[] { ex.getMessage() }));
			}
		}
		return response;
	}

	@RequestMapping("/county")
	public String county(Model model, @RequestParam("city") String cityCode) {
		OriginalCity city = null;
		String collectUrl = "";
		try {
			city = this.originalService.findCity(cityCode);
			collectUrl = this.collectService.getUrl(cityCode);
		} catch (EasysoftException e) {
			logger.error(e.toString());
			model.addAttribute("error", e.getMessage());
			return "common/tab_error";
		}
		model.addAttribute("city", city);
		model.addAttribute("collectUrl", collectUrl);
		return "original/county";
	}

	@RequestMapping(value = "/counties", method = RequestMethod.POST)
	@ResponseBody
	public BootstrapTableResponse<OriginalCounty> getCounties(@RequestBody OriginalCountyPageRequest request) {

		BootstrapTableResponse<OriginalCounty> response = new BootstrapTableResponse<OriginalCounty>();

		Pageable pageable = PageRequest.of(request.getPage(), request.getLimit());
		if (StringUtils.hasText(request.getSort())) {
			pageable = PageRequest.of(request.getPage(), request.getLimit(),
					request.isDesc() ? Direction.DESC : Direction.ASC, request.getSort());
		}

		Page<OriginalCounty> entities = this.originalService.findCounties(request.getCityCode(), request.getCode(),
				request.getName(), pageable);
		if (entities == null || entities.getTotalElements() == 0) {
			return response;
		}
		response.setRows(entities.getContent());
		response.setTotal(entities.getTotalElements());

		return response;
	}

	@RequestMapping("/county_{code}")
	public String countyEdit(Model model, @PathVariable String code) {
		try {
			OriginalCounty entity = this.originalService.findCounty(code);
			model.addAttribute("vm", entity);
			return "original/county_edit";
		} catch (EasysoftException e) {
			logger.error(e.toString());
			model.addAttribute("error", e.getMessage());
			return "common/tab_error";
		}
	}

	@RequestMapping(value = "/county/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse countyEdit(@RequestBody @Valid OriginalCountyEditRequest request,
			BindingResult validatedResult) {

		AjaxResponse response = new AjaxResponse(validatedResult);

		if (response.isResult()) {
			try {
				this.originalService.updateCounty(request.getCode(), request.getName());
				response.setMessage(this.messageSource.getMessage("edit_success"));

			} catch (EasysoftException ex) {
				response.setResult(false);
				response.setMessage(this.messageSource.getMessage("edit_failed", new Object[] { ex.getMessage() }));
			} catch (Exception ex) {
				logger.error(ex.toString());
				response.setResult(false);
				response.setMessage(this.messageSource.getMessage("edit_failed", new Object[] { ex.getMessage() }));
			}
		}
		return response;
	}

	@RequestMapping("/town")
	public String town(Model model, @RequestParam("county") String countyCode) {
		/* 特殊处理，部分地级市没有区县，直接下属乡镇，如广东省中山市，东莞市 */
		String collectUrl = "";
		if (countyCode.length() > 6) {
			if ("00".equals(countyCode.substring(4, 6))) {
				OriginalCity city = null;
				try {
					city = this.originalService.findCity(countyCode);
					collectUrl = this.collectService.getUrl(countyCode);
				} catch (EasysoftException e) {
					logger.error(e.toString());
					model.addAttribute("error", e.getMessage());
					return "common/tab_error";
				}
				model.addAttribute("county", city);
				model.addAttribute("collectUrl", collectUrl);
				return "original/town";
			}
		}

		OriginalCounty county = null;
		try {
			county = this.originalService.findCounty(countyCode);
			collectUrl = this.collectService.getUrl(countyCode);
		} catch (EasysoftException e) {
			logger.error(e.toString());
			model.addAttribute("error", e.getMessage());
			return "common/tab_error";
		}
		model.addAttribute("county", county);
		model.addAttribute("collectUrl", collectUrl);
		return "original/town";
	}

	@RequestMapping(value = "/towns", method = RequestMethod.POST)
	@ResponseBody
	public BootstrapTableResponse<OriginalTown> getTowns(@RequestBody OriginalTownPageRequest request) {

		BootstrapTableResponse<OriginalTown> response = new BootstrapTableResponse<OriginalTown>();

		Pageable pageable = PageRequest.of(request.getPage(), request.getLimit());
		if (StringUtils.hasText(request.getSort())) {
			pageable = PageRequest.of(request.getPage(), request.getLimit(),
					request.isDesc() ? Direction.DESC : Direction.ASC, request.getSort());
		}

		Page<OriginalTown> entities = this.originalService.findTowns(request.getCountyCode(), request.getCode(),
				request.getName(), pageable);
		if (entities == null || entities.getTotalElements() == 0) {
			return response;
		}
		response.setRows(entities.getContent());
		response.setTotal(entities.getTotalElements());

		return response;
	}

	@RequestMapping("/town_{code}")
	public String townEdit(Model model, @PathVariable String code) {
		try {
			OriginalTown entity = this.originalService.findTown(code);
			model.addAttribute("vm", entity);
			return "original/town_edit";
		} catch (EasysoftException e) {
			logger.error(e.toString());
			model.addAttribute("error", e.getMessage());
			return "common/tab_error";
		}
	}

	@RequestMapping(value = "/town/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse townEdit(@RequestBody @Valid OriginalTownEditRequest request, BindingResult validatedResult) {

		AjaxResponse response = new AjaxResponse(validatedResult);

		if (response.isResult()) {
			try {
				this.originalService.updateTown(request.getCode(), request.getName());
				response.setMessage(this.messageSource.getMessage("edit_success"));

			} catch (EasysoftException ex) {
				response.setResult(false);
				response.setMessage(this.messageSource.getMessage("edit_failed", new Object[] { ex.getMessage() }));
			} catch (Exception ex) {
				logger.error(ex.toString());
				response.setResult(false);
				response.setMessage(this.messageSource.getMessage("edit_failed", new Object[] { ex.getMessage() }));
			}
		}
		return response;
	}

	@RequestMapping("/village")
	public String village(Model model, @RequestParam("town") String townCode) {
		OriginalTown town = null;
		String collectUrl = "";
		try {
			town = this.originalService.findTown(townCode);
			collectUrl = this.collectService.getUrl(townCode);
		} catch (EasysoftException e) {
			logger.error(e.toString());
			model.addAttribute("error", e.getMessage());
			return "common/tab_error";
		}
		model.addAttribute("town", town);
		model.addAttribute("collectUrl", collectUrl);
		return "original/village";
	}

	@RequestMapping(value = "/villages", method = RequestMethod.POST)
	@ResponseBody
	public BootstrapTableResponse<OriginalVillage> getVillages(@RequestBody OriginalVillagePageRequest request) {

		BootstrapTableResponse<OriginalVillage> response = new BootstrapTableResponse<OriginalVillage>();

		Pageable pageable = PageRequest.of(request.getPage(), request.getLimit());
		if (StringUtils.hasText(request.getSort())) {
			pageable = PageRequest.of(request.getPage(), request.getLimit(),
					request.isDesc() ? Direction.DESC : Direction.ASC, request.getSort());
		}

		Page<OriginalVillage> entities = this.originalService.findVillages(request.getTownCode(), request.getCode(),
				request.getName(), pageable);
		if (entities == null || entities.getTotalElements() == 0) {
			return response;
		}
		response.setRows(entities.getContent());
		response.setTotal(entities.getTotalElements());

		return response;
	}

	@RequestMapping("/village_{code}")
	public String villageEdit(Model model, @PathVariable String code) {
		try {
			OriginalVillage entity = this.originalService.findVillage(code);
			model.addAttribute("vm", entity);
			return "original/village_edit";
		} catch (EasysoftException e) {
			logger.error(e.toString());
			model.addAttribute("error", e.getMessage());
			return "common/tab_error";
		}
	}

	@RequestMapping(value = "/village/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse villageEdit(@RequestBody @Valid OriginalVillageEditRequest request,
			BindingResult validatedResult) {

		AjaxResponse response = new AjaxResponse(validatedResult);

		if (response.isResult()) {
			try {
				this.originalService.updateVillage(request.getCode(), request.getName());
				response.setMessage(this.messageSource.getMessage("edit_success"));

			} catch (EasysoftException ex) {
				response.setResult(false);
				response.setMessage(this.messageSource.getMessage("edit_failed", new Object[] { ex.getMessage() }));
			} catch (Exception ex) {
				logger.error(ex.toString());
				response.setResult(false);
				response.setMessage(this.messageSource.getMessage("edit_failed", new Object[] { ex.getMessage() }));
			}
		}
		return response;
	}

	@RequestMapping("/all")
	public String all(Model model, HttpServletRequest request) {
		
		super.getLayoutData(model, request);
		
		boolean canImport = this.originalService.canImport();
		model.addAttribute("canImport", canImport);
		return "original/all";
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST)
	@ResponseBody
	public BootstrapTableResponse<OriginalAll> getAll(@RequestBody OriginalAllPageRequest request) {

		BootstrapTableResponse<OriginalAll> response = new BootstrapTableResponse<OriginalAll>();

		Pageable pageable = PageRequest.of(request.getPage(), request.getLimit());
		if (StringUtils.hasText(request.getSort())) {
			pageable = PageRequest.of(request.getPage(), request.getLimit(),
					request.isDesc() ? Direction.DESC : Direction.ASC, request.getSort());
		}

		Page<OriginalAll> entities = this.originalService.findAll(request.getProvinceName(), request.getCityName(),
				request.getCountyName(), request.getTownName(), request.getVillageName(), pageable);
		if (!entities.hasContent()) {
			return response;
		}
		response.setRows(entities.getContent());
		response.setTotal(entities.getTotalElements());

		return response;
	}

	@RequestMapping(value = "/import/all", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse importAll() {

		AjaxResponse response = new AjaxResponse();

		try {
			this.originalService.importToAll();
			response.setMessage(this.messageSource.getMessage("action_success"));
			response.setResult(true);

		} catch (Exception ex) {
			logger.error(ex.toString());
			response.setMessage(this.messageSource.getMessage("action_failed", new Object[] { ex.getMessage() }));
		}
		return response;
	}
	
	@RequestMapping(value = "/export/all", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse exportAll(@RequestBody OriginalAllPageRequest request) {

		AjaxResponse response = new AjaxResponse();

		try {
			String fileName = this.originalService.exportAll(request.getProvinceName(), request.getCityName(), request.getCountyName(), request.getTownName(), request.getVillageName());
			//response.setMessage(this.messageSource.getMessage("action_success"));
			response.setData(fileName);
			response.setResult(true);

		} catch (Exception ex) {
			logger.error(ex.toString());
			response.setMessage(this.messageSource.getMessage("action_failed", new Object[] { ex.getMessage() }));
		}
		return response;
	}
	
	
}
