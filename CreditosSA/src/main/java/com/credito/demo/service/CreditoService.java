package com.credito.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credito.demo.dao.CreditoRepository;
import com.credito.demo.model.Credito;

@Service 
public class CreditoService {
	
//	public CreditoService(){
//	}
	
	@Autowired
	private CreditoRepository creditoRepository;
	
    protected Environment env;
	
	private final Integer PAGE_SIZE = 10;
	
	
	public Page<Credito> getListado(Specification<Credito> search, Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = getPageable(pageNo, pageSize, sortBy);
		return creditoRepository.findAll(search,paging);
	}
	
	private Pageable getPageable(Integer pageNo, Integer pageSize, String sortBy) {                            
        pageSize = (pageSize != null) ? pageSize : PAGE_SIZE;
        Pageable paging;
        paging = PageRequest.of(pageNo, pageSize);
        return paging;
	}
	
	@Transactional
	public List<Credito> guardarCredito(List<Credito> creditos) throws Exception {
		try {
			return creditoRepository.saveAll(creditos);
		}catch(Exception e) {	
			throw new Exception("Error al guardar el credito: " + e);
		}	
	}
	
	
	@Transactional
	public Credito editarCredito(Credito credito, Long id) throws Exception {
		try {
			Credito cred = creditoRepository.findById(id).orElse(null);
			if(cred != null){
				return creditoRepository.save(credito);
			}else {
				throw new Exception("No existe el credito a modificar.");
			}	
		}catch(Exception e) {	
			throw new Exception("Error al guardar el credito: " + e);
		}	
	}
	
	@Transactional
	public Credito eliminarCredito(Long id) throws Exception {
		try {
			Credito cred = creditoRepository.findById(id).orElse(null);
			if(cred != null){
				if(cred.getMontoDeuda() > 0) {
					throw new Exception("No existe el credito a eliminar.");
				}else {
					creditoRepository.delete(cred);
				}
				return cred;
			}else {
				throw new Exception("No existe el credito a eliminar.");
			}	
		}catch(Exception e) {	
			throw new Exception("Error al guardar el credito: " + e);
		}	
	}
	
	
	@Transactional
	public Credito consultarCredito(Long id) throws Exception {
		try {
			Credito cred = creditoRepository.findById(id).orElse(null);
			if(cred != null){
				return cred;
			}else {
				throw new Exception("No existe el credito a consultar.");
			}	
		}catch(Exception e) {	
			throw new Exception("Error al consultar el credito: " + e);
		}	
	}
	
	



}
