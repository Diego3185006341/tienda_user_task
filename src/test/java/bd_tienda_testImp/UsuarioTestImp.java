package bd_tienda_testImp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.tienda.Model.UserEntity;
import com.tienda.Repository.UserRepository;
import com.tienda.Service.imp.UsuarioServiceimp;
import com.tienda.dto.FiltroDetalle;
import com.tienda.dto.FiltrosDto;
import com.tienda.dto.RequestCreateUser;
import com.tienda.dto.ResponseMessage;


import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UsuarioTestImp {
	@Mock
	UserRepository mockrepo;

	@InjectMocks
	UsuarioServiceimp mockusuarioimp;
	
	@Mock
    UserEntity usuariomodel;
	@Mock
    UserEntity mockusuariomodel;


	@BeforeEach
		void contextLoads() {
		MockitoAnnotations.initMocks(this);		
	}
	@Test
	void agregarUsuarios() {
		 //RequestResponseAgregar rq=new RequestResponseAgregar("2323","dad","2","#","dsd");
		 /* Mockito.when(mockrepo.save(Mockito.anyObject())).thenReturn(usuariomodel);
		  ResponseEntity<Object>response=mockusuarioimp.agregarUsuario(RequestResponseAgregar.builder().build());
		  assertEquals(response.getBody().getClass(),RequestResponseAgregar.class);
*/	
        RequestCreateUser request = new RequestCreateUser();
        request.setCedula_Usuario("123");
        request.setNombre_Usuario("John Doe");
        request.setCorreo_Usuario("johndoe@example.com");
        request.setUsuario("johndoe");
        request.setClave_Usuario("password");
        request.setFecha_Ingreso(LocalDate.parse("2022-01-01"));

        when(mockrepo.findById("123")).thenReturn(Optional.empty());

        // When
        ResponseEntity<Object> response = mockusuarioimp.saveUser(request);

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
		RequestCreateUser request = new RequestCreateUser();
		String cedula="123";
        request.setCedula_Usuario("123");
        request.setNombre_Usuario("John Doe");
        request.setCorreo_Usuario("johndoe@example.com");
        request.setUsuario("johndoe");
        request.setClave_Usuario("password");

        UserEntity usuario = new UserEntity();
        when(mockrepo.findById(cedula)).thenReturn(Optional.of(usuario));
        when(mockrepo.save(usuario)).thenReturn(usuario);

        // When
        ResponseEntity<Object> response = mockusuarioimp.updateUser(cedula,request);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
	    
	}
	@Test 
	void consultarUsuario() {
	       String cedula = "123";

	        UserEntity usuario = new UserEntity();
	        usuario.setCedula_Usuario("123");
	        usuario.setNombre_Usuario("John Doe");
	        usuario.setCorreo_Usuario("johndoe@example.com");
	        usuario.setUser("johndoe");
	        usuario.setClave_Usuario("password");
	        usuario.setFecha_Ingreso(LocalDate.parse("2022-01-01"));

	        when(mockrepo.findById(cedula)).thenReturn(Optional.of(usuario));

	        // When
	        ResponseEntity<RequestCreateUser> response = mockusuarioimp.findUserByIdentification(cedula);

	        // Then
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        RequestCreateUser requestResponseAgregar = response.getBody();
	        assertEquals(usuario.getCedula_Usuario(), requestResponseAgregar.getCedula_Usuario());
	        assertEquals(usuario.getNombre_Usuario(), requestResponseAgregar.getNombre_Usuario());
	        assertEquals(usuario.getCorreo_Usuario(), requestResponseAgregar.getCorreo_Usuario());
	        assertEquals(usuario.getUser(), requestResponseAgregar.getUsuario());
	        assertEquals(usuario.getClave_Usuario(), requestResponseAgregar.getClave_Usuario());
	        assertEquals(usuario.getFecha_Ingreso(), requestResponseAgregar.getFecha_Ingreso());
		
	
	}

	/*@Test 
	void deleteUsuario() {
		
		Optional<UsuarioModel>usuario=Optional.of(mab());
		RequestResponseAgregar request=new RequestResponseAgregar();
		 Mockito.when(mockrepo.findById(anyString())).thenReturn((usuario));	
		  ResponseEntity<ResponseUsuario> response=mockusuarioimp.deleteUsuario(request);
		  assertEquals(response.getBody().getClass(),ResponseUsuario.class );
	}*/
	@Test 
	void deleteUsuario() {
		String cedula="00001";

	        // When
	        ResponseEntity<ResponseMessage> response = mockusuarioimp.deleteUser(cedula);

	        // Then
	        verify(mockrepo).deleteById(cedula);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("se elimino usuario", response.getBody().getMessage());
	}
	@Test
	public void testConsultafiltros_filtrosExist() {
		// Given
		FiltrosDto request = new FiltrosDto();
		List<FiltroDetalle> filtros = new ArrayList<>();
		FiltroDetalle filtro = new FiltroDetalle();
		filtro.setParametro("fecha_ingreso");
		filtro.setValor("2022-01-01");
		filtros.add(filtro);
		request.setFiltros(filtros);

		
		    UserEntity usuario = new UserEntity();
		    usuario.setCedula_Usuario("123");
		    usuario.setNombre_Usuario("John Doe");
		    usuario.setCorreo_Usuario("johndoe@example.com");
		    usuario.setUser("johndoe");
		    usuario.setFecha_Ingreso(LocalDate.parse("2022-01-01"));
		    
		    //mocking the repository behavior
		    when(mockrepo.consultaUsuarios(filtro.getValor())).thenReturn(Arrays.asList(usuario));
		    
		    // When
		    ResponseEntity<Object> result = mockusuarioimp.consultafiltros(request);

		    // Then
		    assertEquals(HttpStatus.OK, result.getStatusCode());
		    List<RequestCreateUser> list = (List<RequestCreateUser>) result.getBody();
		    assertEquals(1, list.size());
		    assertEquals("123", list.get(0).getCedula_Usuario());
		    assertEquals("John Doe", list.get(0).getNombre_Usuario());
		    assertEquals("johndoe@example.com", list.get(0).getCorreo_Usuario());
		    assertEquals("johndoe", list.get(0).getUsuario());
		    assertEquals("2022-01-01", list.get(0).getFecha_Ingreso().toString());
	}
		
		
	
}
