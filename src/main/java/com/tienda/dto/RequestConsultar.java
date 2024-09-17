package com.tienda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestConsultar {

	/*private List<Filtros>filtros;
	private String numeroPagina;
	private String numeroRegistros;
	private String orden;
	private String parametroOrdenamiento;
	*/
	
	private String cedula_Usuario;
}
