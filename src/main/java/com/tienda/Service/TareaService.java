package com.tienda.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tienda.Model.TareasModel;
import com.tienda.dto.FiltrosDto;
import com.tienda.dto.RequestCreateTarea;
import com.tienda.dto.ResponseMessage;

public interface TareaService {



	ResponseEntity<List<TareasModel>> listarTarea();

	ResponseEntity<Object> agregarTarea(RequestCreateTarea request);



	ResponseEntity<RequestCreateTarea> consultarTareaid(String id);

	ResponseEntity<Object> modificarTarea(String id, RequestCreateTarea request);

	ResponseEntity<ResponseMessage> deleteTarea(String id);

	ResponseEntity<Object> consultafiltros(FiltrosDto request);

}
