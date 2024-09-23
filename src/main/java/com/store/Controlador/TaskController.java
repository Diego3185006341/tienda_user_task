package com.store.Controlador;

import com.store.Model.TaskEntity;
import com.store.Service.TaskService;
import com.store.dto.RequestCreateTask;
import com.store.dto.ResponseCreateTask;
import com.store.dto.ResponseMessage;
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
	public ResponseEntity<List<TaskEntity>> retrieveAllTask()
	{
		return servicetarea.retrieveAllTasks();
	}
	@PostMapping("/saveTask")
	public ResponseEntity<ResponseCreateTask> saveTask(@RequestBody RequestCreateTask request) {
		
		return servicetarea.saveTask(request);
	}
	@PutMapping("/updateTask/{id}")
	public ResponseEntity<Object> updateTask(@PathVariable Integer id, @RequestBody RequestCreateTask request) {
		
		return servicetarea.updateTask(id,request);
	}
	@GetMapping ("/findByTaskId/{id}")
	public ResponseEntity<RequestCreateTask> findByTaskId(@PathVariable Integer id)
	{
		return  servicetarea.findByTaskId(id);
	}
	@DeleteMapping ("deleteTaskById/{id}")
	public ResponseEntity<ResponseMessage> deleteTaskById(@PathVariable Integer id)
	{
		return servicetarea.deleteTaskById(id);
		
	}
}
