package com.easysoft.cad.ui.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easysoft.cad.domain.entity.CollectUrl;
import com.easysoft.cad.domain.service.CollectService;
import com.easysoft.cad.domain.valueObject.UrlCategory;
import com.easysoft.cad.domain.valueObject.UrlStatus;
import com.easysoft.cad.ui.viewModel.collect.CollectUrlPageRequest;
import com.easysoft.core.util.EasysoftMessageSource;
import com.easysoft.core.web.viewModel.AjaxResponse;
import com.easysoft.core.web.viewModel.BootstrapTableResponse;

@Controller
@RequestMapping("/collect")
public class CollectController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CollectController.class);

	@Autowired
	private CollectService collectService;

	@Autowired
	private EasysoftMessageSource messageSource;

	@RequestMapping("/index")
	public String index(Model model, HttpServletRequest request) {
		
		super.getLayoutData(model, request);

		boolean canCollect = this.collectService.canCollect();
		model.addAttribute("canCollect", canCollect);

		Map<String, String> categoryList = new HashMap<String, String>();
		for (String category : UrlCategory.list()) {
			categoryList.put(category, this.messageSource.getMessage("url_category_" + category));
		}
		model.addAttribute("categoryList", categoryList);

		Map<String, String> statusList = new HashMap<String, String>();
		for (String status : UrlStatus.list()) {
			statusList.put(status, this.messageSource.getMessage("url_status_" + status));
		}
		model.addAttribute("statusList", statusList);

		return "collect/index";
	}

	@RequestMapping(value = "/urls", method = RequestMethod.POST)
	@ResponseBody
	public BootstrapTableResponse<CollectUrl> getProvinces(@RequestBody CollectUrlPageRequest request) {

		BootstrapTableResponse<CollectUrl> response = new BootstrapTableResponse<CollectUrl>();

		Pageable pageable = PageRequest.of(request.getPage(), request.getLimit());
		if (StringUtils.hasText(request.getSort())) {
			pageable = PageRequest.of(request.getPage(), request.getLimit(),
					request.isDesc() ? Direction.DESC : Direction.ASC, request.getSort());
		}

		Page<CollectUrl> entities = this.collectService.findAll(request.getUrl(), request.getUrlCategory(),
				request.getUrlCode(), request.getStatus(), pageable);
		if (entities == null || entities.getTotalElements() == 0) {
			return response;
		}
		response.setRows(entities.getContent());
		response.setTotal(entities.getTotalElements());

		return response;
	}

	@RequestMapping(value = "/start", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse start() {

		AjaxResponse response = new AjaxResponse();
		try {
			if (this.collectService.canCollect()) {
				this.collectService.collect();
				response.setMessage(this.messageSource.getMessage("action_success"));
			} else {
				response.setMessage(this.messageSource.getMessage("no_collect_task"));
			}
			response.setResult(true);
		} catch (Exception ex) {
			logger.error(ex.toString());
			response.setResult(false);
			response.setMessage(this.messageSource.getMessage("action_failed", new Object[] { ex.getMessage() }));
		}
		return response;
	}
}
