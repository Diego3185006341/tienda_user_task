package bd_tienda_testImp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.tienda.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tienda.Model.TareasModel;
import com.tienda.Model.UserEntity;
import com.tienda.Repository.TareaRepository;
import com.tienda.Service.imp.TaskServiceImp;

public class TareaTestimp {
	@Mock
	TareaRepository mockrepo;

	@InjectMocks
	TaskServiceImp mocktareaimp;
	
	@Mock
	 TareasModel tareamodel;
	@Mock
    UserEntity mockusuariomodel;


	@BeforeEach
		void contextLoads() {
		MockitoAnnotations.initMocks(this);		
	}
	 @Test
	void agregarTarea() {
		 //RequestResponseAgregar rq=new RequestResponseAgregar("2323","dad","2","#","dsd");
		 /* Mockito.when(mockrepo.save(Mockito.anyObject())).thenReturn(usuariomodel);
		  ResponseEntity<Object>response=mockusuarioimp.agregarUsuario(RequestResponseAgregar.builder().build());
		  assertEquals(response.getBody().getClass(),RequestResponseAgregar.class);
*/	
        RequestCreateTask request = new RequestCreateTask();
        request.setId("04");
        request.setTask_name("proyectoz");
        request.setDelivery_month("abril");


        when(mockrepo.findById("123")).thenReturn(Optional.empty());

        // When
        ResponseEntity<ResponseCreateTask> response = mocktareaimp.saveTask(request);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(request, response.getBody());
    }
	 
	 
	 
	 @Test 
		void modificarUsuario() {
			 /* Mockito.when(mockrepo.findById(anyString())).thenReturn(Optional.ofNullable(mockusuariomodel));	
			  Mockito.when(mockrepo.save(Mockito.anyObject())).thenReturn(usuariomodel);
			  ResponseEntity<Object>response=mockusuarioimp.agregarUsuario(RequestResponseAgregar.builder().build());
			  assertEquals(response.getBody().getClass(),RequestResponseAgregar.class);*/
			RequestCreateTask request = new RequestCreateTask();
			String cedula="123";
	        request.setId("123");
	        request.setTask_name("proyecto32");
	        request.setDelivery_month("diciembre");
	        
	        TareasModel tarea = new TareasModel();
	        when(mockrepo.findById(cedula)).thenReturn(Optional.of(tarea));
	        when(mockrepo.save(tarea)).thenReturn(tarea);

	        // When
	        ResponseEntity<Object> response = mocktareaimp.updateTask(cedula,request);

	        // Then
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(tarea, response.getBody());
		    
		}
		@Test 
		void consultarTarea() {
		       String cedula = "123";

		        TareasModel tarea = new TareasModel();
		        tarea.setId("123");
		        tarea.setTaskName("Protect");
		        tarea.setDeliveryMonth("enero");
		     

		        when(mockrepo.findById(cedula)).thenReturn(Optional.of(tarea));

		        // When
		        ResponseEntity<RequestCreateTask> response = mocktareaimp.findByTaskId(cedula);

		        // Then
		        assertEquals(HttpStatus.OK, response.getStatusCode());
		        RequestCreateTask requestResponseAgregarTarea = response.getBody();
		        assertEquals(tarea.getId(), requestResponseAgregarTarea.getId());
		        assertEquals(tarea.getTaskName(), requestResponseAgregarTarea.getTask_name());
		        assertEquals(tarea.getDeliveryMonth(), requestResponseAgregarTarea.getDelivery_month());
		        
		
		}
		@Test 
		void deleteTarea() {
			String cedula="00001";

		        // When
		        ResponseEntity<ResponseMessage> response = mocktareaimp.deleteTaskById(cedula);

		        // Then
		        verify(mockrepo).deleteById(cedula);
		        assertEquals(HttpStatus.OK, response.getStatusCode());
		        assertEquals("se elimino Tarea", response.getBody().getMessage());
		}

}
