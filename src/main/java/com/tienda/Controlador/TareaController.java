package com.tienda.Controlador;

import java.util.List;

import com.tienda.dto.ResponseCreateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.Service.TareaService;
import com.tienda.Model.TareasModel;
import com.tienda.dto.FiltrosDto;
import com.tienda.dto.RequestCreateTask;
import com.tienda.dto.ResponseMessage;
@RestController
@RequestMapping("/api")
public class TareaController {
	@Autowired
	TareaService servicetarea;
	
	@GetMapping("/listarTarea")
	public ResponseEntity<List<TareasModel>> listartarea()
	{
		return servicetarea.listarTarea();
	}
	@PostMapping("/agregarTarea")
	public ResponseEntity<ResponseCreateTask> AgregarTarea(@RequestBody RequestCreateTask request) {
		
		return servicetarea.agregarTarea(request);
	}
	@PutMapping("/modificarTarea/{id}")
	public ResponseEntity<Object> modificarTarea (@PathVariable String id,@RequestBody RequestCreateTask request) {
		
		return servicetarea.modificarTarea(id,request );
	}
	@GetMapping ("/consultarTarea/{id}")
	public ResponseEntity<RequestCreateTask> consultarTareaPorid (@PathVariable String id)
	{
		return  servicetarea.consultarTareaid(id);
	}
	@DeleteMapping ("eliminarTarea/{id}")
	public ResponseEntity<ResponseMessage> deleteTarea(@PathVariable String id)
	{
		return servicetarea.deleteTarea(id);
		
	}
	@GetMapping("/filtrosTarea")
	public  ResponseEntity<Object> consultafiltrosTarea (@RequestBody FiltrosDto request ) {
		return servicetarea.consultafiltros(request);
		
	}
}
