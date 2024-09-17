package com.tienda.Service;

import java.util.List;


import org.springframework.http.ResponseEntity;


import com.tienda.Model.UsuarioModel;
import com.tienda.dto.FiltrosDto;
import com.tienda.dto.RequestCreateUser;
import com.tienda.dto.ResponseMessage;

public interface  IUsuarioService {


	 ResponseEntity<List<UsuarioModel>> listarUsuarios();

	
	//public ResponseEntity<ResponseUsuario> deleteUsuario (RequestResponseAgregar request);
	ResponseEntity<ResponseMessage> deleteUsuario (String Cedula);
	 ResponseEntity<Object> agregarUsuario(RequestCreateUser request);
	 ResponseEntity<Object> modificarUsuario(String id, RequestCreateUser request);
	 ResponseEntity<RequestCreateUser> consultarusuario(String Cedula);
	 ResponseEntity<Object> consultafiltros(FiltrosDto request);
	
}
