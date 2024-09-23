package com.store.Service;

import java.util.List;
import java.util.UUID;

import com.store.dto.ResponseCreateTask;
import org.springframework.http.ResponseEntity;

import com.store.Model.TaskEntity;
import com.store.dto.RequestCreateTask;
import com.store.dto.ResponseMessage;

public interface TaskService {



	ResponseEntity<List<TaskEntity>> retrieveAllTasks();

	ResponseEntity<ResponseCreateTask> saveTask(RequestCreateTask request);


	ResponseEntity<RequestCreateTask> findByTaskId(UUID id);

	ResponseEntity<Object> updateTask(UUID id, RequestCreateTask request);

	ResponseEntity<ResponseMessage> deleteTaskById(UUID id);


}
