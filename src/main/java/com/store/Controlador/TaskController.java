package com.store.Controlador;

import com.store.Service.TaskService;
import com.store.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TaskController {
	@Autowired
	TaskService taskService;
	
	@GetMapping("/retrieveAllTask")
	public ResponseEntity<ResponseRetrieveAllTask> retrieveAllTask()
	{
		return taskService.retrieveAllTasks();
	}
	@PostMapping("/saveTask")
	public ResponseEntity<ResponseCreateTask> saveTask(@RequestBody RequestCreateTask request) {
		
		return taskService.saveTask(request);
	}
	@PutMapping("/updateTask/{id}")
	public ResponseEntity<Object> updateTask(@PathVariable UUID id, @RequestBody RequestCreateTask request) {
		
		return taskService.updateTask(id,request);
	}
	@GetMapping ("/findByTaskId/{id}")
	public ResponseEntity<ResponseGetTaskId> findByTaskId(@PathVariable UUID id)
	{
		return  taskService.findByTaskId(id);
	}
	@DeleteMapping ("deleteTaskById/{id}")
	public ResponseEntity<ResponseMessage> deleteTaskById(@PathVariable UUID id)
	{
		return taskService.deleteTaskById(id);
		
	}
}
