package com.bd_tienda_test.Service;

import java.util.List;


import org.springframework.http.ResponseEntity;


import com.bd_tienda_test.Model.UsuarioModel;
import com.bd_tienda_test.dto.FiltrosDto;
import com.bd_tienda_test.dto.RequestResponseAgregar;
import com.bd_tienda_test.dto.ResponseMessage;

public interface IUsuarioService {


	 ResponseEntity<List<UsuarioModel>> listarUsuarios();

	
	//public ResponseEntity<ResponseUsuario> deleteUsuario (RequestResponseAgregar request);
	ResponseEntity<ResponseMessage> deleteUsuario (String Cedula);
	 ResponseEntity<Object> agregarUsuario(RequestResponseAgregar request);
	 ResponseEntity<Object> modificarUsuario(String id,RequestResponseAgregar request);
	 ResponseEntity<RequestResponseAgregar> consultarusuario(String Cedula);
	 ResponseEntity<Object> consultafiltros(FiltrosDto request);
	
}
