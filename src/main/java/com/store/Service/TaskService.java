package com.store.Service;

import java.util.UUID;

import com.store.dto.*;
import org.springframework.http.ResponseEntity;

public interface TaskService {



	ResponseEntity<ResponseRetrieveAllTask> retrieveAllTasks();

	ResponseEntity<ResponseCreateTask> saveTask(RequestCreateTask request);


	ResponseEntity<ResponseGetTaskId> findByTaskId(UUID id);

	ResponseEntity<Object> updateTask(UUID id, RequestCreateTask request);

	ResponseEntity<ResponseMessage> deleteTaskById(UUID id);


}
