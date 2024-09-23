package bd_tienda_testController;

import com.store.Controlador.UserController;
import com.store.Model.UserEntity;
import com.store.Service.IUserService;
import com.store.dto.RequestCreateUser;
import com.store.dto.ResponseCreateUser;
import com.store.dto.ResponseMessage;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Data
public class Tienda_testController {
    @Mock
    IUserService service;
    @Mock
    ResponseEntity<UserEntity> usuariom;


    @InjectMocks
    UserController controller;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void agregarUsuarios() {
        RequestCreateUser request = new RequestCreateUser();
        request.setUser_identification("123");
        request.setUser_name("John Doe");
        request.setUser_email("johndoe@example.com");
        request.setUser("johndoe");
        request.setPassword("password");
        request.setEntry_date(LocalDate.now());

        ResponseCreateUser responseCreateUser = ResponseCreateUser.builder()
                .build();

        Mockito.when(service.saveUser(request)).thenReturn(ResponseEntity.ok().body(responseCreateUser));
        ResponseEntity<ResponseCreateUser> respuesta = controller.saveUser(request);
        assertEquals(respuesta.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(respuesta.getBody()).getClass(), RequestCreateUser.class);
        assertEquals(request, respuesta.getBody());
    }

    @Test
    void modificarUsuario() {
        RequestCreateUser request = new RequestCreateUser();
        String Cedula = "01233";
        Mockito.when(service.updateUser(Cedula, request)).thenReturn(ResponseEntity.ok().body(RequestCreateUser.builder().build()));
        ResponseEntity<Object> respuesta = controller.updateUser(Cedula, request);
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(RequestCreateUser.class, Objects.requireNonNull(respuesta.getBody()).getClass());
    }

    @Test
    void consultarUsuario() {
        String cedula = "00001";
        RequestCreateUser requestResponseAgregar = new RequestCreateUser();
        Mockito.when(service.findUserByIdentification(cedula)).thenReturn(ResponseEntity.ok(requestResponseAgregar));
        ResponseEntity<RequestCreateUser> respuesta = controller.findUserByIdentification(cedula);
        assertEquals(respuesta.getBody(), requestResponseAgregar);
    }

    @Test
    void deleteUsuario() {

        String Cedula = "00001";
        Mockito.when(service.deleteUser(Cedula)).thenReturn(ResponseEntity.ok().body(ResponseMessage.builder().build()));
        ResponseEntity<ResponseMessage> respuesta = controller.deleteUser(Cedula);
        assertEquals(Objects.requireNonNull(respuesta.getBody()).getClass(), ResponseMessage.class);
    }


}
	
	
