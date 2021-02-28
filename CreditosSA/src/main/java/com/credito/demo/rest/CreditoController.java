package com.credito.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;
import com.credito.demo.model.Credito;
import com.credito.demo.service.CreditoService;
import com.sipios.springsearch.anotation.SearchSpec;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/credito")
public class CreditoController {
	@Autowired
	private CreditoService creditoService ;//= new CreditoService();
	
	@GetMapping("/mensaje")
	public String mensaje() {
		return "Hola";
	}
	
	@Operation(summary = "Listar de creditos", description = "Listar de creditos")
	@ApiResponse(responseCode = "200", description = "Listado consultado correctamente")
	@ApiResponse(responseCode = "400", description = "Petición generada incorrectamente")
	@ApiResponse(responseCode = "404", description = "No se identifica el recurso solicitado")
	@ApiResponse(responseCode = "500", description = "Internal Error - No se puede procesar la petición solicitada")
	@GetMapping("/listarcredito")
    public ResponseEntity<Page<Credito>> listado(    	
    	@SearchSpec Specification<Credito> search,
		@RequestParam(defaultValue = "0") Integer pageNo,
		@RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "id:asc") String sortBy){
		Page<Credito> list = creditoService.getListado(search, pageNo, pageSize, sortBy);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
	}
	
	@Operation(summary = "Guardar creditos", description = "Guardar creditos")
	@ApiResponse(responseCode = "200", description = "Listado consultado correctamente")
	@ApiResponse(responseCode = "400", description = "Petición generada incorrectamente")
	@ApiResponse(responseCode = "404", description = "No se identifica el recurso solicitado")
	@ApiResponse(responseCode = "500", description = "Internal Error - No se puede procesar la petición solicitada")
	@PostMapping("/guardar")
    public ResponseEntity<List<Credito>> guardarCredito( @Valid @RequestBody List<Credito> creditos ) throws Exception{
		List<Credito> list = creditoService.guardarCredito(creditos);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
	}

	
	@Operation(summary = "Modificar credito", description = "Modificar credito")
	@ApiResponse(responseCode = "200", description = "Proceso realizado correctamente")
	@ApiResponse(responseCode = "400", description = "Petición generada incorrectamente")
	@ApiResponse(responseCode = "404", description = "No se identifica el recurso solicitado")
	@ApiResponse(responseCode = "500", description = "Internal Error - No se puede procesar la petición solicitada")
	@PutMapping("/modificar/{id}")
    public ResponseEntity<Credito> modificarCredito( @Valid @RequestBody Credito credito, @PathVariable Long id ) throws Exception{
		Credito cred = creditoService.editarCredito(credito, id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(cred);
	}
	
	
	@Operation(summary = "Consultar un credito", description = "Consultar un credito")
	@ApiResponse(responseCode = "200", description = "Credito consultado correctamente")
	@ApiResponse(responseCode = "400", description = "Petición generada incorrectamente")
	@ApiResponse(responseCode = "404", description = "No se identifica el recurso solicitado")
	@ApiResponse(responseCode = "500", description = "Internal Error - No se puede procesar la petición solicitada")
	@GetMapping("/consultarcredito/{id}")
    public ResponseEntity<Credito> consultarCredito( @PathVariable Long id) throws Exception{
		Credito credito = creditoService.consultarCredito(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(credito);
	}
	
	
	@Operation(summary = "Eliminar un credito", description = "Eliminar un credito")
	@ApiResponse(responseCode = "200", description = "Credito consultado correctamente")
	@ApiResponse(responseCode = "400", description = "Petición generada incorrectamente")
	@ApiResponse(responseCode = "404", description = "No se identifica el recurso solicitado")
	@ApiResponse(responseCode = "500", description = "Internal Error - No se puede procesar la petición solicitada")
	@GetMapping("/eliminarcredito/{id}")
    public ResponseEntity<Credito> eliminarCredito( @PathVariable Long id) throws Exception{
		Credito credito = creditoService.eliminarCredito(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(credito);
	}
}
