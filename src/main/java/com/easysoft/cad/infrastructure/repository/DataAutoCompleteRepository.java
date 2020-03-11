package com.easysoft.cad.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.easysoft.cad.domain.entity.DataAutoComplete;

public interface DataAutoCompleteRepository extends JpaRepository<DataAutoComplete, String>, JpaSpecificationExecutor<DataAutoComplete> {

	
}
