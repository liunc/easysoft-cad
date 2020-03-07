package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.easysoft.cad.domain.entity.OriginalAll;

public interface OriginalAllRepository extends PagingAndSortingRepository<OriginalAll, String>, JpaSpecificationExecutor<OriginalAll> {

}
