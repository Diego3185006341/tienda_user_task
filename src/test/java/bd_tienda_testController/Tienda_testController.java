package bd_tienda_testController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tienda.Controlador.UserController;
import com.tienda.Service.IUserService;
import com.tienda.Model.UserEntity;
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
	IUserService service;
	@Mock
	ResponseEntity<UserEntity> usuariom;


	@InjectMocks
	UserController controller;
	
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
	    Mockito.when(service.saveUser(request)).thenReturn(ResponseEntity.ok().body(request));
	    ResponseEntity<Object>respuesta=controller.saveUser(request);
	    assertEquals(respuesta.getStatusCode(), HttpStatus.OK);
	    assertEquals(respuesta.getBody().getClass(), RequestCreateUser.class);
	    assertEquals(request, respuesta.getBody());
	}
	@Test
	void modificarUsuario(){
	RequestCreateUser request = new RequestCreateUser();
	String Cedula="01233";
	Mockito.when(service.updateUser(Cedula,request)).thenReturn(ResponseEntity.ok().body(RequestCreateUser.builder().build()));
	ResponseEntity<Object>respuesta=controller.updateUser(Cedula,request);
	assertEquals(HttpStatus.OK, respuesta.getStatusCode());
	assertEquals(RequestCreateUser.class, respuesta.getBody().getClass());
	}
	@Test
	void consultarUsuario(){
		String cedula="00001";
		RequestCreateUser requestResponseAgregar = new RequestCreateUser();
		Mockito.when(service.findUserByIdentification(cedula)).thenReturn(ResponseEntity.ok(requestResponseAgregar));
		ResponseEntity<RequestCreateUser> respuesta=controller.findUserByIdentification(cedula);
		assertEquals(respuesta.getBody(), requestResponseAgregar);
		}
	@Test
	void deleteUsuario(){

	String Cedula="00001";
	Mockito.when(service.deleteUser(Cedula)).thenReturn(ResponseEntity.ok().body(ResponseMessage.builder().build()));
	ResponseEntity<ResponseMessage> respuesta=controller.deleteUser(Cedula);
	assertEquals(respuesta.getBody().getClass(),ResponseMessage.class);
	}
	@Test
	void testConsultafiltros() {
	    // Given
	    FiltrosDto request = new FiltrosDto();
	    request.setFiltros(Arrays.asList(new FiltroDetalle("fecha_ingreso", "2022-01-01")));

	    List<UserEntity> expectedData = Arrays.asList(
	        new UserEntity("123", "diego", "d@example.com", "ddoe", "password", LocalDate.parse("2022-01-01")));

	    // When
	    Mockito.when(service.consultafiltros(request)).thenReturn(ResponseEntity.ok().body(expectedData));
	    ResponseEntity<Object> result = controller.consultafiltros(request);

	    // Then
	    assertEquals(HttpStatus.OK, result.getStatusCode());
	    assertEquals(expectedData, result.getBody());
	}
	
	}
	
	
