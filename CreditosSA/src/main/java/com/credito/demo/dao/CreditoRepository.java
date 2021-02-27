package com.credito.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.credito.demo.model.Credito;

@Repository
public interface  CreditoRepository extends JpaRepository<Credito, Long>, JpaSpecificationExecutor<Credito>{

	
}
