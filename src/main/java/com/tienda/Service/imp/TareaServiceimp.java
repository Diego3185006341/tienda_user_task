package com.tienda.Service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.tienda.Model.UsuarioModel;
import com.tienda.Service.ITiendaService;
import com.tienda.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tienda.Service.TareaService;
import com.tienda.Model.TareasModel;
import com.tienda.Repository.TareaRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
@AllArgsConstructor
public class TareaServiceimp implements TareaService{
	@Autowired
	TareaRepository tarear;
	public final ITiendaService iTiendaService;



	@Override
	public ResponseEntity<List<TareasModel>> listarTarea() {
		// TODO Auto-generated method stub
			try {
			List<TareasModel> tarea =new ArrayList<>();
			
			tarear.findAll().forEach(tarea ::add);
			return new ResponseEntity<>(tarea,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public ResponseEntity<ResponseCreateTask> agregarTarea(RequestCreateTask request) {
		// TODO Auto-generated method stub
		try {
			 tarear.findById(request.getId_Tarea())
					.ifPresent(tareasModel -> {
						throw new IllegalArgumentException("task with ID " + request.getId_Tarea() + " already exists");
					});
				UsuarioModel user = iTiendaService.getUsuario(request.getUsuario_cedula());
				TareasModel buildTask = getBuildTask(request, user);
				tarear.save(buildTask);
			return new ResponseEntity<>(ResponseCreateTask.
					builder()
					.code("200")
					.message("SUCCESS")
					.task(buildTask)
					.build(), HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>(ResponseCreateTask.
					builder()
					.code("400")
					.message(e.getMessage())
					.build(), HttpStatus.BAD_REQUEST);
		}
	}

	private static TareasModel getBuildTask(RequestCreateTask request, UsuarioModel user) {
		return TareasModel.builder()
				.id_Tarea(request.getId_Tarea())
				.nombre_Tarea(request.getNombre_Tarea())
				.mes_Entrega(request.getMes_Entrega())
				.usuario(user)
				.build();
	}

	@Override
	public ResponseEntity<RequestCreateTask> consultarTareaid(String id) {
		// TODO Auto-generated method stub
		 try {
			   Optional<TareasModel>u=tarear.findById(id);
				
				if(u.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					
				}
				else {
					TareasModel consulta=u.get();
					RequestCreateTask respuesta=new RequestCreateTask();
					respuesta.setId_Tarea(consulta.getId_Tarea());
					respuesta.setNombre_Tarea(consulta.getNombre_Tarea());
					respuesta.setMes_Entrega(consulta.getMes_Entrega());
					
					return new ResponseEntity<>(respuesta,HttpStatus.OK);
				}
			
		} catch (Exception e) {
			return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}

	@Override
	public ResponseEntity<Object> modificarTarea(String id, RequestCreateTask request) {
		// TODO Auto-generated method stub
		try {
			Optional<TareasModel>u=tarear.findById(id);
				
			if(u.isEmpty()) {
				
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			}else {
				TareasModel usuario=u.get();
				usuario.setId_Tarea(request.getId_Tarea());
				usuario.setNombre_Tarea(request.getNombre_Tarea());
				usuario.setMes_Entrega(request.getMes_Entrega());

					
			return new ResponseEntity<>(tarear.save(usuario),HttpStatus.OK);
			
			
			}	
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseMessage> deleteTarea(String id) {
		// TODO Auto-generated method stub
		try {
			ResponseMessage response=new ResponseMessage();
			tarear.deleteById(id);
			response.setMessage("se elimino Tarea");
			return new ResponseEntity<>(response,HttpStatus.OK);
			
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
			}
	}

	@Override
	public ResponseEntity<Object> consultafiltros(FiltrosDto request) {
		// TODO Auto-generated method stub
		try {

			String  nombre_tarea = null;
			String mes_entrega = null;
			if (request.getFiltros() == null || request.getFiltros().isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			} else {
				Map<String, String> obtenerFiltros = filtrosUsuario(request.getFiltros());

				nombre_tarea = obtenerFiltros.get("nombre_tarea") != null?(obtenerFiltros.get("nombre_tarea"))
						: null;
				mes_entrega = obtenerFiltros.get("mes_entrega")!= null?(obtenerFiltros.get("mes_entrega"))
						: null;
			}

			List<TareasModel> consultaData = tarear.consultarTarea(nombre_tarea,mes_entrega);
			
			List<RequestCreateTask> respuesta = new ArrayList<>();
			consultaData.forEach(value -> respuesta.add(RequestCreateTask.builder().id_Tarea(value.getId_Tarea())
					.nombre_Tarea(value.getNombre_Tarea()).mes_Entrega(value.getMes_Entrega())
					.build()));

					

			return ResponseEntity.ok(respuesta);
		
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	private Map<String, String> filtrosUsuario(List<FiltroDetalle> filtros) {
		Map<String, String> returnFiltros = new HashMap<>();

		for (FiltroDetalle filtro : filtros) {
			returnFiltros.put(filtro.getParametro(), filtro.getValor());
		}

		return returnFiltros;
	}
	
	



	

	
	
	
	
	

}
