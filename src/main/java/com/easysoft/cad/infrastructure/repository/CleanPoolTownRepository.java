package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.cad.domain.entity.CleanPoolTown;

/**F
 * 乡、镇、社区仓储接口
 * 
 * @author 刘年超
 */
public interface CleanPoolTownRepository extends JpaRepository<CleanPoolTown, String>, JpaSpecificationExecutor<CleanPoolTown> {

	@Modifying
	@Transactional(rollbackFor = Exception.class)
	@Query("insert into CleanPoolTown(code, name) select o.code, o.name from OriginalTown o")
	public void importData();
}
 