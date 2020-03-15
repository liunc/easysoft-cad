package com.easysoft.cad.infrastructure.repository;

import java.util.List;

import com.easysoft.cad.domain.entity.CollectUrl;
import com.easysoft.core.data.jpa.repository.BaseRepository;

/**
 * 采集数据Url仓储接口
 * 
 * @author 刘年超
 */
public interface CollectUrlRepository extends BaseRepository<CollectUrl, String> {

	public CollectUrl findByUrlCode(String urlCode);
	
	public List<CollectUrl> findByStatusNot(String status);
	
	public long countByStatusNot(String status);
}
