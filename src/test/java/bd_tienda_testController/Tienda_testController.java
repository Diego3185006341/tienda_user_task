package bd_tienda_testController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tienda.Controlador.UsuarioControlador;
import com.tienda.Service.IUsuarioService;
import com.tienda.Model.UsuarioModel;
import com.tienda.dto.FiltroDetalle;
import com.tienda.dto.FiltrosDto;
import com.tienda.dto.RequestCreateUser;
import com.tienda.dto.ResponseMessage;

import lombok.Data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Data
public class Tienda_testController {
	@Mock
	IUsuarioService service;
	@Mock
	ResponseEntity<UsuarioModel> usuariom;


	@InjectMocks
	UsuarioControlador controller;
	
	@BeforeEach
	public void setUp()throws Exception {
		MockitoAnnotations.initMocks(this);
			
	}
	@Test
	void agregarUsuarios(){
		RequestCreateUser request = new RequestCreateUser();
	    request.setCedula_Usuario("123");
	    request.setNombre_Usuario("John Doe");
	    request.setCorreo_Usuario("johndoe@example.com");
	    request.setUsuario("johndoe");
	    request.setClave_Usuario("password");
	    request.setFecha_Ingreso(LocalDate.now());
	    Mockito.when(service.agregarUsuario(request)).thenReturn(ResponseEntity.ok().body(request));
	    ResponseEntity<Object>respuesta=controller.AgregarUsuario(request);
	    assertEquals(respuesta.getStatusCode(), HttpStatus.OK);
	    assertEquals(respuesta.getBody().getClass(), RequestCreateUser.class);
	    assertEquals(request, respuesta.getBody());
	}
	@Test
	void modificarUsuario(){
	RequestCreateUser request = new RequestCreateUser();
	String Cedula="01233";
	Mockito.when(service.modificarUsuario(Cedula,request)).thenReturn(ResponseEntity.ok().body(RequestCreateUser.builder().build()));
	ResponseEntity<Object>respuesta=controller.modificarUsuario(Cedula,request);
	assertEquals(HttpStatus.OK, respuesta.getStatusCode());
	assertEquals(RequestCreateUser.class, respuesta.getBody().getClass());
	}
	@Test
	void consultarUsuario(){
		String cedula="00001";
		RequestCreateUser requestResponseAgregar = new RequestCreateUser();
		Mockito.when(service.consultarusuario(cedula)).thenReturn(ResponseEntity.ok(requestResponseAgregar));
		ResponseEntity<RequestCreateUser> respuesta=controller.consultarUsuarioPorCedula(cedula);
		assertEquals(respuesta.getBody(), requestResponseAgregar);
		}
	@Test
	void deleteUsuario(){

	String Cedula="00001";
	Mockito.when(service.deleteUsuario(Cedula)).thenReturn(ResponseEntity.ok().body(ResponseMessage.builder().build()));
	ResponseEntity<ResponseMessage> respuesta=controller.deleteUsuario(Cedula);
	assertEquals(respuesta.getBody().getClass(),ResponseMessage.class);
	}
	@Test
	void testConsultafiltros() {
	    // Given
	    FiltrosDto request = new FiltrosDto();
	    request.setFiltros(Arrays.asList(new FiltroDetalle("fecha_ingreso", "2022-01-01")));

	    List<UsuarioModel> expectedData = Arrays.asList(
	        new UsuarioModel("123", "diego", "d@example.com", "ddoe", "password", LocalDate.parse("2022-01-01")));

	    // When
	    Mockito.when(service.consultafiltros(request)).thenReturn(ResponseEntity.ok().body(expectedData));
	    ResponseEntity<Object> result = controller.consultafiltros(request);

	    // Then
	    assertEquals(HttpStatus.OK, result.getStatusCode());
	    assertEquals(expectedData, result.getBody());
	}
	
	}
	
	
