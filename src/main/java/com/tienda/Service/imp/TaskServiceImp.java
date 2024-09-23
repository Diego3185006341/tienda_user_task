package com.tienda.Service.imp;

import com.tienda.Model.TareasModel;
import com.tienda.Model.UserEntity;
import com.tienda.Repository.TareaRepository;
import com.tienda.Service.ITiendaService;
import com.tienda.Service.TaskService;
import com.tienda.Utils.Mappers;
import com.tienda.dto.RequestCreateTask;
import com.tienda.dto.ResponseCreateTask;
import com.tienda.dto.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class TaskServiceImp implements TaskService {
    @Autowired
    TareaRepository tareaRepository;
    public final ITiendaService iTiendaService;


    @Override
    public ResponseEntity<List<TareasModel>> retrieveAllTasks() {
        // TODO Auto-generated method stub
        try {
            List<TareasModel> task = new ArrayList<>(tareaRepository.findAll());
            return new ResponseEntity<>(task, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<ResponseCreateTask> saveTask(RequestCreateTask request) {
        // TODO Auto-generated method stub
        try {
            tareaRepository.findById(request.getId())
                    .ifPresent(tareasModel -> {
                        throw new IllegalArgumentException("task with ID " + request.getId() + " already exists");
                    });
            UserEntity user = iTiendaService.getUsuario(request.getUser_id());
            TareasModel buildTask = Mappers.getBuildTask(request, user);
            tareaRepository.save(buildTask);
            return new ResponseEntity<>(ResponseCreateTask.
                    builder()
                    .code("200")
                    .message("SUCCESS")
                    .task(buildTask)
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
    public ResponseEntity<RequestCreateTask> findByTaskId(String id) {
        // TODO Auto-generated method stub
        try {
            Optional<TareasModel> u = tareaRepository.findById(id);

            if (u.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                TareasModel consulta = u.get();
                RequestCreateTask respuesta = new RequestCreateTask();
                respuesta.setId(consulta.getId());
                respuesta.setTask_name(consulta.getTaskName());
                respuesta.setDelivery_month(consulta.getDeliveryMonth());

                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Object> updateTask(String id, RequestCreateTask request) {
        // TODO Auto-generated method stub
        try {
            Optional<TareasModel> u = tareaRepository.findById(id);

            if (u.isEmpty()) {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                TareasModel usuario = u.get();
                usuario.setId(request.getId());
                usuario.setTaskName(request.getTask_name());
                usuario.setDeliveryMonth(request.getDelivery_month());


                return new ResponseEntity<>(tareaRepository.save(usuario), HttpStatus.OK);


            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage> deleteTaskById(String id) {
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
