package com.easysoft.cad.infrastructure.repository;

import com.easysoft.cad.domain.entity.OriginalTown;
import com.easysoft.core.data.jpa.repository.BaseRepository;

/**F
 * 乡、镇、社区仓储接口
 * 
 * @author 刘年超
 */
public interface OriginalTownRepository extends BaseRepository<OriginalTown, String> {

	public OriginalTown findByCode(String code);
}
 