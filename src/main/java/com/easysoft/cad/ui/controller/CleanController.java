package com.easysoft.cad.ui.controller;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.easysoft.cad.domain.entity.CleanRule;
import com.easysoft.cad.domain.service.CleanRuleService;
import com.easysoft.cad.domain.valueObject.DataCategory;
import com.easysoft.cad.ui.viewModel.clean.CleanRuleAddRequest;
import com.easysoft.cad.ui.viewModel.clean.CleanRuleEditRequest;
import com.easysoft.cad.ui.viewModel.clean.CleanRulePageRequest;
import com.easysoft.core.util.EasysoftException;
import com.easysoft.core.util.EasysoftMessageSource;
import com.easysoft.core.web.viewModel.AjaxResponse;
import com.easysoft.core.web.viewModel.BootstrapTableResponse;

@Controller
@RequestMapping("/clean")
public class CleanController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CleanController.class);

	@Autowired
	private CleanRuleService cleanRuleService;

	@Autowired
	private EasysoftMessageSource messageSource;

	@RequestMapping("/rule")
	public String rule(Model model, HttpServletRequest request) {
		
		super.getLayoutData(model, request);

		Map<String, String> categoryList = new HashMap<String, String>();
		for (String category : DataCategory.list()) {
			categoryList.put(category, this.messageSource.getMessage("data_category_" + category));
		}
		model.addAttribute("categoryList", categoryList);

		return "clean/rule";
	}

	@RequestMapping(value = "/rules", method = RequestMethod.POST)
	@ResponseBody
	public BootstrapTableResponse<CleanRule> getProvinces(@RequestBody CleanRulePageRequest request) {

		BootstrapTableResponse<CleanRule> response = new BootstrapTableResponse<CleanRule>();

		Pageable pageable = PageRequest.of(request.getPage(), request.getLimit());
		if (StringUtils.hasText(request.getSort())) {
			pageable = PageRequest.of(request.getPage(), request.getLimit(),
					request.isDesc() ? Direction.DESC : Direction.ASC, request.getSort());
		}

		Page<CleanRule> entities = this.cleanRuleService.findAll(request.getDataCategory(), pageable);
		if (entities == null || entities.getTotalElements() == 0) {
			return response;
		}
		response.setRows(entities.getContent());
		response.setTotal(entities.getTotalElements());

		return response;
	}

	@RequestMapping("/rule_add")
	public String ruleAdd(Model model) {

		Map<String, String> categoryList = new HashMap<String, String>();
		for (String category : DataCategory.list()) {
			categoryList.put(category, this.messageSource.getMessage("data_category_" + category));
		}
		model.addAttribute("categoryList", categoryList);

		return "clean/rule_add";
	}
	
	@RequestMapping(value = "/rule/add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse ruleAdd(@RequestBody @Valid CleanRuleAddRequest request,
			BindingResult validatedResult) {

		AjaxResponse response = new AjaxResponse(validatedResult);

		if (response.isResult()) {
			try {
				this.cleanRuleService.add(request.getDataCategory(), request.getSearchText(), request.getReplaceText(), request.getPriority());
				response.setMessage(this.messageSource.getMessage("add_success"));

			} catch (EasysoftException ex) {
				response.setResult(false);
				response.setMessage(this.messageSource.getMessage("add_failed", new Object[] { ex.getMessage() }));
			} catch (Exception ex) {
				logger.error(ex.toString());
				response.setResult(false);
				response.setMessage(this.messageSource.getMessage("add_failed", new Object[] { ex.getMessage() }));
			}
		}
		return response;
	}
	
	@RequestMapping("/rule_{id}")
	public String ruleEdit(Model model, @PathVariable String id) {

		try {
			CleanRule entity = this.cleanRuleService.find(id);
			model.addAttribute("dataCategory", this.messageSource.getMessage("data_category_"+ entity.getDataCategory()));
			model.addAttribute("vm", entity);
			return "clean/rule_edit";
		} catch (EasysoftException e) {
			logger.error(e.toString());
			model.addAttribute("error", e.getMessage());
			return "common/modal_error";
		}
	}
	
	@RequestMapping(value = "/rule/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse ruleEdit(@RequestBody @Valid CleanRuleEditRequest request,
			BindingResult validatedResult) {

		AjaxResponse response = new AjaxResponse(validatedResult);

		if (response.isResult()) {
			try {
				this.cleanRuleService.update(request.getId(), request.getSearchText(), request.getReplaceText(), request.getPriority());
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
	
	@RequestMapping(value = "/rule/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse ruleDelete(@RequestBody String id) {

		AjaxResponse response = new AjaxResponse();
		try {
			this.cleanRuleService.delete(id);
			response.setResult(true);
			response.setMessage(this.messageSource.getMessage("delete_success"));
		} catch (Exception ex) {
			logger.error(ex.toString());
			response.setResult(false);
			response.setMessage(this.messageSource.getMessage("delete_failed", new Object[] { ex.getMessage() }));
		}
		return response;
	}
}
