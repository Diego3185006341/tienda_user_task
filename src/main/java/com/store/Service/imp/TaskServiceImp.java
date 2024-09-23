package com.store.Service.imp;

import com.store.Model.TaskEntity;
import com.store.Model.UserEntity;
import com.store.Repository.TaskRepository;
import com.store.Service.ITiendaService;
import com.store.Service.TaskService;
import com.store.Utils.Mappers;
import com.store.dto.RequestCreateTask;
import com.store.dto.ResponseCreateTask;
import com.store.dto.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class TaskServiceImp implements TaskService {
    @Autowired
    TaskRepository tareaRepository;
    public final ITiendaService iTiendaService;


    @Override
    public ResponseEntity<List<TaskEntity>> retrieveAllTasks() {
        // TODO Auto-generated method stub
        try {
            List<TaskEntity> task = new ArrayList<>(tareaRepository.findAll());
            return new ResponseEntity<>(task, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<ResponseCreateTask> saveTask(RequestCreateTask request) {
        // TODO Auto-generated method stub
        try {
            UserEntity user = iTiendaService.getUsuario(request.getUser_id());
            TaskEntity buildTask = Mappers.getBuildTask(request, user);
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
    public ResponseEntity<RequestCreateTask> findByTaskId(UUID id) {
        // TODO Auto-generated method stub
        try {
            Optional<TaskEntity> u = tareaRepository.findById(id);

            if (u.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                TaskEntity consulta = u.get();
                RequestCreateTask respuesta = new RequestCreateTask();
                respuesta.setTask_name(consulta.getTaskName());
                respuesta.setDelivery_month(consulta.getDeliveryMonth());

                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Object> updateTask(UUID id, RequestCreateTask request) {
        // TODO Auto-generated method stub
        try {
            Optional<TaskEntity> u = tareaRepository.findById(id);

            if (u.isEmpty()) {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                TaskEntity usuario = u.get();
                usuario.setTaskName(request.getTask_name());
                usuario.setDeliveryMonth(request.getDelivery_month());


                return new ResponseEntity<>(tareaRepository.save(usuario), HttpStatus.OK);


            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
