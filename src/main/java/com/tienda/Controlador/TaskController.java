package com.tienda.Controlador;

import com.tienda.Model.TareasModel;
import com.tienda.Service.TaskService;
import com.tienda.dto.RequestCreateTask;
import com.tienda.dto.ResponseCreateTask;
import com.tienda.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class TaskController {
	@Autowired
	TaskService servicetarea;
	
	@GetMapping("/retrieveAllTask")
	public ResponseEntity<List<TareasModel>> retrieveAllTask()
	{
		return servicetarea.retrieveAllTasks();
	}
	@PostMapping("/saveTask")
	public ResponseEntity<ResponseCreateTask> saveTask(@RequestBody RequestCreateTask request) {
		
		return servicetarea.saveTask(request);
	}
	@PutMapping("/updateTask/{id}")
	public ResponseEntity<Object> updateTask(@PathVariable String id, @RequestBody RequestCreateTask request) {
		
		return servicetarea.updateTask(id,request );
	}
	@GetMapping ("/findByTaskId/{id}")
	public ResponseEntity<RequestCreateTask> findByTaskId(@PathVariable String id)
	{
		return  servicetarea.findByTaskId(id);
	}
	@DeleteMapping ("deleteTaskById/{id}")
	public ResponseEntity<ResponseMessage> deleteTaskById(@PathVariable String id)
	{
		return servicetarea.deleteTaskById(id);
		
	}
}
