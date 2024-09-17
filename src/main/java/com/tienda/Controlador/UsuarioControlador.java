package com.tienda.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tienda.Service.IUsuarioService;
import com.tienda.Model.UsuarioModel;
import com.tienda.dto.FiltrosDto;
import com.tienda.dto.RequestCreateUser;
import com.tienda.dto.ResponseMessage;





@CrossOrigin(origins = "*")
@RestController
//@RequestMapping
@RequestMapping("/api")
public class UsuarioControlador {

	@Autowired
	private IUsuarioService serviceUsuario;

	

	
	@GetMapping("/listarUsuario")
	public ResponseEntity<List<UsuarioModel>> listarUsuario()
	{
		return serviceUsuario.listarUsuarios();
	}
	
		
	/*@PostMapping("/saveUsuario")
	public boolean saveUsuario (@RequestBody UsuarioModel u) {
		serviceUsuario.saveUsuario(u);
		return true;
	}*/	
	
	//@GetMapping("/newUsuario")
	//public String agregarUsuario (Model model) {
	//	model.addAttribute("usuario", new UsuarioModel());
	//	return "formUsuarios.html";
	//}
	

	
	/*@PutMapping("/updateUsuario/{Cedula}")
	public boolean actualizarUsuarioPorCedula(@PathVariable String Cedula, UsuarioModel u) {
		Optional<UsuarioModel>usuario=serviceUsuario.listarIdUsuario(Cedula);
		if(usuario.isPresent()) {
			serviceUsuario.update(u);
		return true;
		}else {
			return false;
		}
	}
	*/
	
	/*@DeleteMapping ("eliminarUsuario/{Cedula}")
	public ResponseEntity<ResponseUsuario> deleteUsuario (@RequestBody RequestResponseAgregar request)
	{
		return serviceUsuario.deleteUsuario(request);
		
	}*/
	@DeleteMapping ("eliminarUsuario/{Cedula}")
	public ResponseEntity<ResponseMessage> deleteUsuario (@PathVariable String Cedula)
	{
		return serviceUsuario.deleteUsuario(Cedula);
		
	}
	@PostMapping("/agregarUsuario")
	public ResponseEntity<Object> AgregarUsuario (@RequestBody RequestCreateUser request) {
		
		return serviceUsuario.agregarUsuario(request);
	}	

	@PutMapping("/modificarUsuario/{id}")
	public ResponseEntity<Object> modificarUsuario (@PathVariable String id,@RequestBody RequestCreateUser request) {
		
		return serviceUsuario.modificarUsuario(id,request );
	}
	

	@GetMapping ("/consultarUsuario/{Cedula}")
	public ResponseEntity<RequestCreateUser> consultarUsuarioPorCedula (@PathVariable String Cedula)
	{
		return  serviceUsuario.consultarusuario(Cedula);
	}
	
	@GetMapping("/filtros")
	public  ResponseEntity<Object> consultafiltros (@RequestBody FiltrosDto request ) {
		return serviceUsuario.consultafiltros(request);
		
	}
}
