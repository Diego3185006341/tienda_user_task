package com.store.Service.imp;

import com.store.Model.TaskEntity;
import com.store.Repository.TaskRepository;
import com.store.Service.ITiendaService;
import com.store.Service.TaskService;
import com.store.Utils.Mappers;
import com.store.dto.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class TaskServiceImp implements TaskService {
    @Autowired
    TaskRepository tareaRepository;
    public final ITiendaService iTiendaService;


    @Override
    public ResponseEntity<ResponseRetrieveAllTask> retrieveAllTasks() {
        // TODO Auto-generated method stub
        try {
            List<TaskEntity> tasks = new ArrayList<>(tareaRepository.findAll());

            List<TaskDtoResponse> list = tasks.stream().map(Mappers::getBuildResponseTaskDto).collect(Collectors.toList());
            return new ResponseEntity<>(ResponseRetrieveAllTask.
                    builder()
                    .code("200")
                    .message("SUCESS")
                    .retrieve_tasks(list)
                    .build(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<ResponseCreateTask> saveTask(RequestCreateTask request) {
        // TODO Auto-generated method stub
        try {
            iTiendaService.getUsuario(request.getUser_id());
            TaskEntity buildTask = Mappers.getBuildTask(request);
            tareaRepository.save(buildTask);
            return new ResponseEntity<>(ResponseCreateTask.
                    builder()
                    .code("200")
                    .message("SUCCESS")
                    .task_id(buildTask.getId())
                    .build(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseCreateTask.
                    builder()
                    .code("400")
                    .message(e.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<ResponseGetTaskId> findByTaskId(UUID id) {
        // TODO Auto-generated method stub
        try {
            TaskEntity task = tareaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not task found"));
            return new ResponseEntity<>(ResponseGetTaskId.builder()
                    .code("200")
                    .message("SUCCESS")
                    .task_id(task.getId())
                    .task_name(task.getTaskName())
                    .user_id(task.getUserID())
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Object> updateTask(UUID id, RequestCreateTask request) {
        // TODO Auto-generated method stub
        try {
            tareaRepository.findById(id).orElseThrow(() -> new NotFoundException("task doesn't exist"));
            TaskEntity buildTaskUpdate = Mappers.getBuildTaskUpdate(request, id);
            tareaRepository.save(buildTaskUpdate);
            return new ResponseEntity<>(ResponseUpdateTask
                    .builder()
                    .code("200")
                    .message("SUCCESS")
                    .task(Mappers.getBuildResponseTaskDto(buildTaskUpdate))
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseUpdateTask.builder()
                    .code("400").message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<ResponseMessage> deleteTaskById(UUID id) {
        // TODO Auto-generated method stub
        try {
            ResponseMessage response = new ResponseMessage();
            tareaRepository.deleteById(id);
            response.setMessage("se elimino Tarea");
            return new ResponseEntity<>(response, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
