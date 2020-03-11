package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.easysoft.cad.domain.entity.OriginalAll;

public interface OriginalAllRepository extends JpaRepository<OriginalAll, String>, JpaSpecificationExecutor<OriginalAll> {

}
