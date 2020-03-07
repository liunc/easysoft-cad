package com.easysoft.cad.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.easysoft.cad.domain.entity.CleanRule;
import com.easysoft.cad.domain.service.CleanRuleService;
import com.easysoft.cad.infrastructure.repository.CleanRuleRepository;
import com.easysoft.core.util.EasysoftException;
import com.easysoft.core.util.EasysoftMessageSource;

@Service
public class CleanRuleServiceImpl implements CleanRuleService {

	@Autowired
	private CleanRuleRepository cleanRuleRepository;

	@Autowired
	private EasysoftMessageSource messageSource;

	@Override
	public Page<CleanRule> findAll(String dataCategory, Pageable pageable) {
		return this.cleanRuleRepository.findAll(new Specification<CleanRule>() {

			private static final long serialVersionUID = -3950053446063792958L;

			@Override
			public Predicate toPredicate(Root<CleanRule> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();

				if (StringUtils.hasText(dataCategory)) {
					list.add(criteriaBuilder.equal(root.get("dataCategory").as(String.class), dataCategory));
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
	public void add(String dataCategory, String searchText, String replaceText, int priority) throws EasysoftException {

		boolean exists = this.cleanRuleRepository.existsByDataCategoryAndSearchText(dataCategory,
				searchText);
		if (exists) {
			throw new EasysoftException(this.messageSource.getMessage("data_exists",
					new Object[] { this.messageSource.getMessage("search_text"), searchText }));
		}
		CleanRule entity = new CleanRule();
		entity.create(dataCategory, searchText, replaceText, priority);
		this.cleanRuleRepository.save(entity);
	}

	@Override
	public void update(String id, String searchText, String replaceText, int priority) throws EasysoftException {

		CleanRule entity = this.find(id);
		if (!entity.getSearchText().equals(searchText)) {
			boolean exists = this.cleanRuleRepository
					.existsByDataCategoryAndSearchText(entity.getDataCategory(), searchText);
			if (exists) {
				throw new EasysoftException(this.messageSource.getMessage("data_exists",
						new Object[] { this.messageSource.getMessage("search_text"), searchText }));
			}
		}
		entity.update(searchText, replaceText, priority);
		this.cleanRuleRepository.save(entity);
	}

	@Override
	public void delete(String id) throws EasysoftException {
		

	}

	@Override
	public CleanRule find(String id) throws EasysoftException {
		return this.cleanRuleRepository.findById(id)
				.orElseThrow(() -> new EasysoftException(this.messageSource.getMessage("data_not_found", new Object[] {
						this.messageSource.getMessage("clean_rule"), this.messageSource.getMessage("id"), id })));
	}
}
