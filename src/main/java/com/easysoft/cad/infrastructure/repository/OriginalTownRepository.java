package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.easysoft.cad.domain.entity.OriginalTown;

/**F
 * 乡、镇、社区仓储接口
 * 
 * @author 刘年超
 */
public interface OriginalTownRepository extends PagingAndSortingRepository<OriginalTown, String>, JpaSpecificationExecutor<OriginalTown> {

	public OriginalTown findByCode(String code);
}
 