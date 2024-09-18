package com.tienda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class RequestCreateTask {
	private String id_Tarea;
	private String nombre_Tarea;
	private String mes_Entrega;

	private String usuario_cedula;

}
