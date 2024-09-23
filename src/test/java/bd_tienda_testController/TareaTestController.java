package bd_tienda_testController;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.store.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.store.Controlador.TaskController;
import com.store.Service.TaskService;
import com.store.Model.TaskEntity;

import java.util.UUID;

public class TareaTestController {
	@Mock
	TaskService service;
	@Mock
	ResponseEntity<TaskEntity> usuariom;


	@InjectMocks
	TaskController controller;
	
	@BeforeEach
	public void setUp()throws Exception {
		MockitoAnnotations.initMocks(this);
			
	}
	@Test
	 void agregarTarea(){
		RequestCreateTask request = new RequestCreateTask();
	    request.setTask_name("proxy");
	    request.setDelivery_month("marzo");
		ResponseCreateTask response = new ResponseCreateTask();
	    Mockito.when(service.saveTask(request)).thenReturn(ResponseEntity.ok().body(response));
	    ResponseEntity<ResponseCreateTask> respuesta = controller.saveTask(request);
	    assertEquals(respuesta.getStatusCode(), HttpStatus.OK);
	    assertEquals(respuesta.getBody().getClass(), RequestCreateTask.class);
	    assertEquals(request, respuesta.getBody());
	}
	@Test
	void modificarTarea(){
	RequestCreateTask request = new RequestCreateTask();
		UUID Cedula=UUID.randomUUID();
	Mockito.when(service.updateTask(Cedula,request)).thenReturn(ResponseEntity.ok().body(RequestCreateTask.builder().build()));
	ResponseEntity<Object>respuesta=controller.updateTask(Cedula,request);
	assertEquals(HttpStatus.OK, respuesta.getStatusCode());
	assertEquals(RequestCreateTask.class, respuesta.getBody().getClass());
	}
	@Test
	void consultarTarea(){
		UUID cedula= UUID.randomUUID();
		RequestCreateTask requestResponseAgregar = new RequestCreateTask();
		Mockito.when(service.findByTaskId(cedula)).thenReturn(ResponseEntity.ok(requestResponseAgregar));
		ResponseEntity<RequestCreateTask> respuesta=controller.findByTaskId(cedula);
		assertEquals(respuesta.getBody(), requestResponseAgregar);
		}
	@Test
	void deleteTarea(){

		UUID Cedula= UUID.randomUUID();
	Mockito.when(service.deleteTaskById(Cedula)).thenReturn(ResponseEntity.ok().body(ResponseMessage.builder().build()));
	ResponseEntity<ResponseMessage> respuesta=controller.deleteTaskById(Cedula);
	assertEquals(respuesta.getBody().getClass(),ResponseMessage.class);
	}

	
}
