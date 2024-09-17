package com.tienda.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class RequestCreateUser {
	private String cedula_Usuario;
	private String nombre_Usuario;
	private String correo_Usuario;
	private String usuario;
	private String clave_Usuario;
	private LocalDate fecha_Ingreso;
	

}
