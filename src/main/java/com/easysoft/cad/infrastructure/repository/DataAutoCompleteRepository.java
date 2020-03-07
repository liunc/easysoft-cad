package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.easysoft.cad.domain.entity.DataAutoComplete;

public interface DataAutoCompleteRepository extends PagingAndSortingRepository<DataAutoComplete, String>, JpaSpecificationExecutor<DataAutoComplete> {

	
}
