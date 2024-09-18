package com.tienda.Service;

import java.util.List;

import com.tienda.dto.ResponseCreateTask;
import org.springframework.http.ResponseEntity;

import com.tienda.Model.TareasModel;
import com.tienda.dto.FiltrosDto;
import com.tienda.dto.RequestCreateTask;
import com.tienda.dto.ResponseMessage;

public interface TareaService {



	ResponseEntity<List<TareasModel>> listarTarea();

	ResponseEntity<ResponseCreateTask> agregarTarea(RequestCreateTask request);



	ResponseEntity<RequestCreateTask> consultarTareaid(String id);

	ResponseEntity<Object> modificarTarea(String id, RequestCreateTask request);

	ResponseEntity<ResponseMessage> deleteTarea(String id);

	ResponseEntity<Object> consultafiltros(FiltrosDto request);

}
