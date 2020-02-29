package com.easysoft.cad.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.easysoft.cad.domain.entity.CollectUrl;

public interface CollectUrlService {

	public void collect();
	
	public boolean canCollect();
	
	public Page<CollectUrl> findAll(String url, String urlCategory, String urlCode, String status, Pageable pageable);
	
	public String getUrl(String urlCode);
}
                   