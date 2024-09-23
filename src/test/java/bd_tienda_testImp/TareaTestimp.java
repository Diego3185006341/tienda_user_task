package bd_tienda_testImp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.store.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.store.Model.TaskEntity;
import com.store.Model.UserEntity;
import com.store.Repository.TaskRepository;
import com.store.Service.imp.TaskServiceImp;

public class TareaTestimp {
	@Mock
	TaskRepository mockrepo;

	@InjectMocks
	TaskServiceImp mocktareaimp;
	
	@Mock
	TaskEntity tareamodel;
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
        request.setTask_name("proyectoz");
        request.setDelivery_month("abril");


        when(mockrepo.findById(2)).thenReturn(Optional.empty());

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
			Integer cedula=2;
	        request.setTask_name("proyecto32");
	        request.setDelivery_month("diciembre");
	        
	        TaskEntity tarea = new TaskEntity();
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
		       Integer cedula = 2;

		        TaskEntity tarea = new TaskEntity();
		        tarea.setId(cedula);
		        tarea.setTaskName("Protect");
		        tarea.setDeliveryMonth("enero");
		     

		        when(mockrepo.findById(cedula)).thenReturn(Optional.of(tarea));

		        // When
		        ResponseEntity<RequestCreateTask> response = mocktareaimp.findByTaskId(cedula);

		        // Then
		        assertEquals(HttpStatus.OK, response.getStatusCode());
		        RequestCreateTask requestResponseAgregarTarea = response.getBody();
		        assertEquals(tarea.getTaskName(), requestResponseAgregarTarea.getTask_name());
		        assertEquals(tarea.getDeliveryMonth(), requestResponseAgregarTarea.getDelivery_month());
		        
		
		}
		@Test 
		void deleteTarea() {
			Integer cedula=2;

		        // When
		        ResponseEntity<ResponseMessage> response = mocktareaimp.deleteTaskById(cedula);

		        // Then
		        verify(mockrepo).deleteById(cedula);
		        assertEquals(HttpStatus.OK, response.getStatusCode());
		        assertEquals("se elimino Tarea", response.getBody().getMessage());
		}

}
