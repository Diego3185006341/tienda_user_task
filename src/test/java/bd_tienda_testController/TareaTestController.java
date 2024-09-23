package bd_tienda_testController;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.tienda.Model.UserEntity;
import com.tienda.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tienda.Controlador.TaskController;
import com.tienda.Service.TaskService;
import com.tienda.Model.TareasModel;

public class TareaTestController {
	@Mock
	TaskService service;
	@Mock
	ResponseEntity<TareasModel> usuariom;


	@InjectMocks
	TaskController controller;
	
	@BeforeEach
	public void setUp()throws Exception {
		MockitoAnnotations.initMocks(this);
			
	}
	@Test
	 void agregarTarea(){
		RequestCreateTask request = new RequestCreateTask();
	    request.setId("123");
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
	String Cedula="01233";
	Mockito.when(service.updateTask(Cedula,request)).thenReturn(ResponseEntity.ok().body(RequestCreateTask.builder().build()));
	ResponseEntity<Object>respuesta=controller.updateTask(Cedula,request);
	assertEquals(HttpStatus.OK, respuesta.getStatusCode());
	assertEquals(RequestCreateTask.class, respuesta.getBody().getClass());
	}
	@Test
	void consultarTarea(){
		String cedula="00001";
		RequestCreateTask requestResponseAgregar = new RequestCreateTask();
		Mockito.when(service.findByTaskId(cedula)).thenReturn(ResponseEntity.ok(requestResponseAgregar));
		ResponseEntity<RequestCreateTask> respuesta=controller.findByTaskId(cedula);
		assertEquals(respuesta.getBody(), requestResponseAgregar);
		}
	@Test
	void deleteTarea(){

	String Cedula="00001";
	Mockito.when(service.deleteTaskById(Cedula)).thenReturn(ResponseEntity.ok().body(ResponseMessage.builder().build()));
	ResponseEntity<ResponseMessage> respuesta=controller.deleteTaskById(Cedula);
	assertEquals(respuesta.getBody().getClass(),ResponseMessage.class);
	}

	
}
