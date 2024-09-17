package bd_tienda_testController;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.tienda.Model.UsuarioModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tienda.Controlador.TareaController;
import com.tienda.Service.TareaService;
import com.tienda.Model.TareasModel;
import com.tienda.dto.FiltroDetalle;
import com.tienda.dto.FiltrosDto;
import com.tienda.dto.RequestCreateTarea;
import com.tienda.dto.ResponseMessage;

public class TareaTestController {
	@Mock
	TareaService service;
	@Mock
	ResponseEntity<TareasModel> usuariom;


	@InjectMocks
	TareaController controller;
	
	@BeforeEach
	public void setUp()throws Exception {
		MockitoAnnotations.initMocks(this);
			
	}
	@Test
	 void agregarTarea(){
		RequestCreateTarea request = new RequestCreateTarea();
	    request.setId_Tarea("123");
	    request.setNombre_Tarea("proxy");
	    request.setMes_Entrega("marzo");
	    
	    Mockito.when(service.agregarTarea(request)).thenReturn(ResponseEntity.ok().body(request));
	    ResponseEntity<Object>respuesta=controller.AgregarTarea(request);
	    assertEquals(respuesta.getStatusCode(), HttpStatus.OK);
	    assertEquals(respuesta.getBody().getClass(), RequestCreateTarea.class);
	    assertEquals(request, respuesta.getBody());
	}
	@Test
	void modificarTarea(){
	RequestCreateTarea request = new RequestCreateTarea();
	String Cedula="01233";
	Mockito.when(service.modificarTarea(Cedula,request)).thenReturn(ResponseEntity.ok().body(RequestCreateTarea.builder().build()));
	ResponseEntity<Object>respuesta=controller.modificarTarea(Cedula,request);
	assertEquals(HttpStatus.OK, respuesta.getStatusCode());
	assertEquals(RequestCreateTarea.class, respuesta.getBody().getClass());
	}
	@Test
	void consultarTarea(){
		String cedula="00001";
		RequestCreateTarea requestResponseAgregar = new RequestCreateTarea();
		Mockito.when(service.consultarTareaid(cedula)).thenReturn(ResponseEntity.ok(requestResponseAgregar));
		ResponseEntity<RequestCreateTarea> respuesta=controller.consultarTareaPorid(cedula);
		assertEquals(respuesta.getBody(), requestResponseAgregar);
		}
	@Test
	void deleteTarea(){

	String Cedula="00001";
	Mockito.when(service.deleteTarea(Cedula)).thenReturn(ResponseEntity.ok().body(ResponseMessage.builder().build()));
	ResponseEntity<ResponseMessage> respuesta=controller.deleteTarea(Cedula);
	assertEquals(respuesta.getBody().getClass(),ResponseMessage.class);
	}
	@Test
	void testConsultafiltrosTarea() {
	    // Given
	    FiltrosDto request = new FiltrosDto();
	    request.setFiltros(Arrays.asList(new FiltroDetalle("nombre_tarea", "diego")));
		UsuarioModel usuarioModel = UsuarioModel.builder().build();


	    List<TareasModel> expectedData = Arrays.asList(
	        new TareasModel("123", "diego", "marzo",usuarioModel ));

	    // When
	    Mockito.when(service.consultafiltros(request)).thenReturn(ResponseEntity.ok().body(expectedData));
	    ResponseEntity<Object> result = controller.consultafiltrosTarea(request);

	    // Then
	    assertEquals(HttpStatus.OK, result.getStatusCode());
	    assertEquals(expectedData, result.getBody());
	}
	
}
