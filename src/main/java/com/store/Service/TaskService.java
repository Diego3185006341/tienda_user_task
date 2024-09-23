package com.store.Service;

import java.util.List;

import com.store.dto.ResponseCreateTask;
import org.springframework.http.ResponseEntity;

import com.store.Model.TaskEntity;
import com.store.dto.RequestCreateTask;
import com.store.dto.ResponseMessage;

public interface TaskService {



	ResponseEntity<List<TaskEntity>> retrieveAllTasks();

	ResponseEntity<ResponseCreateTask> saveTask(RequestCreateTask request);


	ResponseEntity<RequestCreateTask> findByTaskId(Integer id);

	ResponseEntity<Object> updateTask(Integer id, RequestCreateTask request);

	ResponseEntity<ResponseMessage> deleteTaskById(Integer id);


}
