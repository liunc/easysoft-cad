package com.easysoft.cad.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.easysoft.cad.domain.entity.CollectUrl;

/**
 * 采集数据Url仓储接口
 * 
 * @author 刘年超
 */
public interface CollectUrlRepository extends PagingAndSortingRepository<CollectUrl, String>, JpaSpecificationExecutor<CollectUrl> {

	public CollectUrl findByUrlCode(String urlCode);
	
	public List<CollectUrl> findByStatusNot(String status);
	
	public long countByStatusNot(String status);
}
