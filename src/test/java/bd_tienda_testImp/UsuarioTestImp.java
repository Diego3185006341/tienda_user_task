package bd_tienda_testImp;
import static org.junit.jupiter.api.Assertions.assertEquals;

import MockFactory.MockFactory;
import com.store.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.store.Model.UserEntity;
import com.store.Repository.UserRepository;
import com.store.Service.imp.userServiceImp;


import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

public class UsuarioTestImp {
	@Mock
	UserRepository mockrepo;

	@InjectMocks
	userServiceImp mockusuarioimp;
	
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
        request.setUser_identification("123");
        request.setUser_name("John Doe");
        request.setUser_email("johndoe@example.com");
        request.setUser("johndoe");
        request.setPassword("password");
        request.setEntry_date(LocalDate.parse("2022-01-01"));

        when(mockrepo.findById("123")).thenReturn(Optional.empty());

        // When
        ResponseEntity<ResponseCreateUser> response = mockusuarioimp.saveUser(request);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(response, response.getBody());
    }
		
	
	@Test 
	void modificarUsuario() {
		 /* Mockito.when(mockrepo.findById(anyString())).thenReturn(Optional.ofNullable(mockusuariomodel));	
		  Mockito.when(mockrepo.save(Mockito.anyObject())).thenReturn(usuariomodel);
		  ResponseEntity<Object>response=mockusuarioimp.agregarUsuario(RequestResponseAgregar.builder().build());
		  assertEquals(response.getBody().getClass(),RequestResponseAgregar.class);*/
		RequestCreateUser request = new RequestCreateUser();
		String id="123";
        request.setUser_identification(id);
        request.setUser_name("John Doe");
        request.setUser_email("johndoe@example.com");
        request.setUser("johndoe");
        request.setPassword("password");

        UserEntity usuario = new UserEntity();
        when(mockrepo.findById(id)).thenReturn(Optional.of(usuario));
        when(mockrepo.save(usuario)).thenReturn(usuario);

        // When
        ResponseEntity<Object> response = mockusuarioimp.updateUser(id,request);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
	    
	}
	@Test 
	void consultarUsuario() {
	       String cedula = "123";


	        when(mockrepo.findById(cedula)).thenReturn(Optional.of(MockFactory.userEntity()));

	        // When
	        ResponseEntity<RequestCreateUser> response = mockusuarioimp.findUserByIdentification(cedula);

	        // Then
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        RequestCreateUser requestResponseAgregar = response.getBody();
	        assertEquals(MockFactory.userEntity(), requestResponseAgregar);

		
	
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
	        assertEquals("se elimino usuario", Objects.requireNonNull(response.getBody()).getMessage());
	}

	
}
