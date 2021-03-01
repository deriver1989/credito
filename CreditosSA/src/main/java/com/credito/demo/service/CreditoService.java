package com.credito.demo.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credito.demo.dao.CreditoRepository;
import com.credito.demo.model.Credito;

@Service 
public class CreditoService {
	
	@Autowired
	private CreditoRepository creditoRepository;
	
    protected Environment env;
	
	private final Integer PAGE_SIZE = 10;
	
	Logger log = LoggerFactory.getLogger(CreditoService.class);
	
	public Page<Credito> getListado(Specification<Credito> search, Integer pageNo, Integer pageSize, String sortBy) {
		log.trace("Implementando el Metodo Listado");
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
		log.trace("Implementando el Metodo Guardar");
		try {
			return creditoRepository.saveAll(creditos);
		}catch(Exception e) {	
			log.error("Error al guardar el credito.");
			throw new Exception("Error al guardar el credito: " + e);
		}	
	}
	
	
	@Transactional
	public Credito editarCredito(Credito credito, Long id) throws Exception {
		log.trace("Implementando el Metodo Editar");
		try {
			Credito cred = creditoRepository.findById(id).orElse(null);
			if(cred != null){
				credito.setId(id);
				return creditoRepository.save(credito);
			}else {
				log.error("No existe el credito a modificars.");
				throw new Exception("No existe el credito a modificar.");
			}	
		}catch(Exception e) {	
			log.error("Error al editar el credito");
			throw new Exception("Error al guardar el credito: " + e);
		}	
	}
	
	@Transactional
	public Credito eliminarCredito(Long id) throws Exception {
		log.trace("Implementando el Metodo Eliminar");
		try {
			Credito cred = creditoRepository.findById(id).orElse(null);
			if(cred != null){
				if(cred.getMontoDeuda() > 0) {
					throw new Exception("El credito aún tiene saldo pendiente.");
				}else {
					creditoRepository.delete(cred);
				}
				return cred;
			}else {
				log.error("No existe el credito a eliminar.");
				throw new Exception("No existe el credito a eliminar.");
			}	
		}catch(Exception e) {
			log.error("Error al guardar el credito");
			throw new Exception("Error al guardar el credito: " + e);
		}	
	}
	
	
	@Transactional
	public Credito consultarCredito(Long id) throws Exception {
		log.trace("Implementando el Metodo Consultar credito por id");
		try {
			Credito cred = creditoRepository.findById(id).orElse(null);
			if(cred != null){
				return cred;
			}else {
				log.error("Error, No existe el credito a consultar");
				throw new Exception("No existe el credito a consultar.");
			}	
		}catch(Exception e) {	
			log.error("Error al consultar el credito");
			throw new Exception("Error al consultar el credito: " + e);
		}	
	}
	
}
