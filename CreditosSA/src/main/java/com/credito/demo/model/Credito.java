package com.credito.demo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CREDITO")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Credito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="IDCLIENTE" , length = 15)
	private String idCliente;
	
	@Column(name="NOMBRE" , length = 60)
	private String nombre;
	
	@Column(name="CORREO" , length = 60)
	private String correoElectronico;
	
	@Column(name="MONTO_DEUDA" , length = 20)
	private Long montoDeuda;

	@Column(name="ID_DEUDA" , length = 15)
	private Long idDeuda;
	
	@Column(name="FECHA")
	private LocalDate fecha;

	
}
