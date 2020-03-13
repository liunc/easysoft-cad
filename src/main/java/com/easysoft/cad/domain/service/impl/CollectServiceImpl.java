package com.easysoft.cad.domain.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.easysoft.cad.CustomProperties;
import com.easysoft.cad.domain.entity.CollectUrl;
import com.easysoft.cad.domain.entity.OriginalCity;
import com.easysoft.cad.domain.entity.OriginalCounty;
import com.easysoft.cad.domain.entity.OriginalProvince;
import com.easysoft.cad.domain.entity.OriginalTown;
import com.easysoft.cad.domain.entity.OriginalVillage;
import com.easysoft.cad.domain.service.CollectService;
import com.easysoft.cad.domain.valueObject.UrlCategory;
import com.easysoft.cad.domain.valueObject.UrlStatus;
import com.easysoft.cad.infrastructure.repository.CollectUrlRepository;
import com.easysoft.cad.infrastructure.repository.OriginalCityRepository;
import com.easysoft.cad.infrastructure.repository.OriginalCountyRepository;
import com.easysoft.cad.infrastructure.repository.OriginalProvinceRepository;
import com.easysoft.cad.infrastructure.repository.OriginalTownRepository;
import com.easysoft.cad.infrastructure.repository.OriginalVillageRepository;
import com.easysoft.core.util.EasysoftException;
import com.easysoft.core.util.EasysoftMessageSource;

@Service
public class CollectServiceImpl implements CollectService {

	private static final Logger logger = LoggerFactory.getLogger(CollectServiceImpl.class);

	@Autowired
	private EasysoftMessageSource messageSource;

	@Autowired
	private CollectUrlRepository collectUrlRepository;

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
	private CustomProperties properties;

	protected Document requestWebPage(String collectUrl) throws EasysoftException {

		int i = 0;
		String errorMessage = "";
		String baseUrl = collectUrl.substring(0, collectUrl.lastIndexOf('/') + 1);

		while (i <= 5) {
			try {
				URL url = new URL(collectUrl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setConnectTimeout(8 * 1000);
				connection.setReadTimeout(8 * 1000);
				connection.setInstanceFollowRedirects(false);
				return Jsoup.parse(connection.getInputStream(), "GBK", baseUrl);
			} catch (Exception e) {
				errorMessage = this.messageSource.getMessage("request_web_url_failed",
						new Object[] { i + 1, e.getMessage(), collectUrl, baseUrl });
			}
			logger.error(errorMessage);
			i++;
		}
		throw new EasysoftException(errorMessage);
	}

	private CollectUrl createCollectUrl(String url, String dataCategory, String dataCode) {
		CollectUrl collectUrl = new CollectUrl();
		collectUrl.create(url, dataCategory, dataCode);
		return collectUrl;
	}

	@Transactional(rollbackFor = Exception.class)
	protected <T, ID> Iterable<CollectUrl> save(CollectUrl current, Iterable<CollectUrl> children,
			CrudRepository<T, ID> dataRepository, Iterable<T> entities) {
		if (children != null) {
			children = this.collectUrlRepository.saveAll(children);
		}
		dataRepository.saveAll(entities);
		this.collectUrlRepository.save(current);
		return children;
	}

	private Iterable<CollectUrl> getProvince(CollectUrl current, Elements trs) {

		List<CollectUrl> children = new ArrayList<CollectUrl>();
		List<OriginalProvince> entities = new ArrayList<OriginalProvince>();
		for (Element tr : trs) {
			for (Element anchor : tr.select("a")) {

				String code = anchor.attr("href").replace(".html", "");
				String name = anchor.text();

				children.add(this.createCollectUrl(anchor.absUrl("href"), UrlCategory.CITY, code));

				OriginalProvince entity = new OriginalProvince();
				entity.create(code, name);
				entities.add(entity);
			}
		}

		return this.save(current, children, this.originalProvinceRepository, entities);
	}

	private Iterable<CollectUrl> getCity(CollectUrl current, Elements trs) {

		List<CollectUrl> children = new ArrayList<CollectUrl>();
		List<OriginalCity> entities = new ArrayList<OriginalCity>();
		for (Element tr : trs) {
			Elements anchors = tr.select("td a");
			if (anchors != null && anchors.size() > 0) {

				String code = anchors.get(0).text();
				String name = anchors.get(1).text();

				children.add(this.createCollectUrl(anchors.get(0).attr("abs:href"), UrlCategory.COUNTY, code));

				OriginalCity entity = new OriginalCity();
				entity.create(code, name);
				entities.add(entity);
			}
		}
		return this.save(current, children, this.originalCityRepository, entities);
	}

	private Iterable<CollectUrl> getCounty(CollectUrl current, Elements trs) {

		List<CollectUrl> children = new ArrayList<CollectUrl>();
		List<OriginalCounty> entities = new ArrayList<OriginalCounty>();
		for (Element tr : trs) {
			Elements anchors = tr.select("td a");
			if (anchors != null && anchors.size() > 0) {

				String code = anchors.get(0).text();
				String name = anchors.get(1).text();

				children.add(this.createCollectUrl(anchors.get(0).attr("abs:href"), UrlCategory.TOWN, code));

				OriginalCounty entity = new OriginalCounty();
				entity.create(code, name);
				entities.add(entity);
			}

		}
		return this.save(current, children, this.originalCountyRepository, entities);
	}

	private Iterable<CollectUrl> getTown(CollectUrl current, Elements trs) {

		List<CollectUrl> children = new ArrayList<CollectUrl>();
		List<OriginalTown> entities = new ArrayList<OriginalTown>();
		for (Element tr : trs) {
			Elements anchors = tr.select("td a");
			if (anchors != null && anchors.size() > 0) {

				String code = anchors.get(0).text();
				String name = anchors.get(1).text();

				children.add(this.createCollectUrl(anchors.get(0).attr("abs:href"), UrlCategory.VILLAGE, code));

				OriginalTown entity = new OriginalTown();
				entity.create(code, name);
				entities.add(entity);
			}
		}
		return this.save(current, children, this.originalTownRepository, entities);
	}

	private Iterable<CollectUrl> getVillage(CollectUrl current, Elements trs) {

		List<OriginalVillage> entities = new ArrayList<OriginalVillage>();
		for (Element tr : trs) {
			Elements tds = tr.select("td");
			if (tds != null && tds.size() > 0) {

				String code = tds.get(0).text();
				String name = tds.get(2).text();
				String category = tds.get(1).text();

				OriginalVillage entity = new OriginalVillage();
				entity.create(code, name, category);
				entities.add(entity);
			}
		}
		return this.save(current, null, this.originalVillageRepository, entities);
	}

	private void requestData(CollectUrl current) {

		Iterable<CollectUrl> children = null;
		Document document;
		try {
			document = this.requestWebPage(current.getUrl());
			current.success();
		} catch (Exception e) {
			current.fail(e.getMessage());
			this.collectUrlRepository.save(current);
			return;
		}

		Elements trs = document.getElementsByClass("provincetr");
		if (trs.size() > 0) {
			children = this.getProvince(current, trs);
		} else {
			trs = document.getElementsByClass("citytr");
			if (trs.size() > 0) {
				children = this.getCity(current, trs);
			} else {
				trs = document.getElementsByClass("countytr");
				if (trs.size() > 0) {
					children = this.getCounty(current, trs);
				} else {
					trs = document.getElementsByClass("towntr");
					if (trs.size() > 0) {
						children = this.getTown(current, trs);
					} else {
						trs = document.getElementsByClass("villagetr");
						if (trs.size() > 0) {
							children = this.getVillage(current, trs);
						}
					}
				}
			}
		}
		if (children != null) {
			for (CollectUrl child : children) {
				this.requestData(child);
			}
		}
	}

	@Async("taskExecutor")
	@Override
	public void collect() {

		long count = this.collectUrlRepository.count();
		if (count == 0) {
			this.collectUrlRepository.save(this.createCollectUrl(properties.getEntryUrl(), UrlCategory.PROVINCE, ""));
		}

		for (CollectUrl current : this.collectUrlRepository.findByStatusNot(UrlStatus.SUCCESS)) {
			this.requestData(current);
		}
	}

	@Override
	public boolean canCollect() {
		long count = this.collectUrlRepository.count();
		if (count == 0) {
			return true;
		}

		count = this.collectUrlRepository.countByStatusNot(UrlStatus.SUCCESS);
		if (count == 0) {
			return false;
		}
		return true;
	}

	@Override
	public Page<CollectUrl> findAll(String url, String urlCategory, String urlCode, String status, Pageable pageable) {

		return this.collectUrlRepository.findAll(new Specification<CollectUrl>() {

			private static final long serialVersionUID = -3950053446063792958L;

			@Override
			public Predicate toPredicate(Root<CollectUrl> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();

				if (StringUtils.hasText(url)) {
					list.add(criteriaBuilder.like(root.get("url").as(String.class), "%" + url + "%"));
				}

				if (StringUtils.hasText(urlCategory)) {
					list.add(criteriaBuilder.like(root.get("urlCategory").as(String.class), "%" + urlCategory + "%"));
				}

				if (StringUtils.hasText(urlCode)) {
					list.add(criteriaBuilder.like(root.get("urlCode").as(String.class), "%" + urlCode + "%"));
				}

				if (StringUtils.hasText(status)) {
					list.add(criteriaBuilder.like(root.get("status").as(String.class), "%" + status + "%"));
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
	public String getUrl(String urlCode) {
		CollectUrl entity = this.collectUrlRepository.findByUrlCode(urlCode);
		if (entity == null) {
			return "";
		}
		return entity.getUrl();
	}
}
