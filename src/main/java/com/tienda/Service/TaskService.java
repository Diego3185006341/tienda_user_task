package com.tienda.Service;

import java.util.List;

import com.tienda.dto.ResponseCreateTask;
import org.springframework.http.ResponseEntity;

import com.tienda.Model.TareasModel;
import com.tienda.dto.RequestCreateTask;
import com.tienda.dto.ResponseMessage;

public interface TaskService {



	ResponseEntity<List<TareasModel>> retrieveAllTasks();

	ResponseEntity<ResponseCreateTask> saveTask(RequestCreateTask request);


	ResponseEntity<RequestCreateTask> findByTaskId(String id);

	ResponseEntity<Object> updateTask(String id, RequestCreateTask request);

	ResponseEntity<ResponseMessage> deleteTaskById(String id);


}
