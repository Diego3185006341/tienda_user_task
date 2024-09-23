package com.tienda.Utils;

import com.tienda.Model.TareasModel;
import com.tienda.Model.UserEntity;
import com.tienda.dto.RequestCreateTask;
import com.tienda.dto.RequestCreateUser;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class Mappers {

    public static TareasModel getBuildTask(RequestCreateTask request, UserEntity user) {
        return TareasModel.builder()
                .id_Tarea(request.getId_Tarea())
                .nombre_Tarea(request.getNombre_Tarea())
                .mes_Entrega(request.getMes_Entrega())
                .usuario(user)
                .build();
    }

    public static UserEntity getBuildUser(RequestCreateUser request) {
        return UserEntity.builder()
                .userIdentification(request.getUser_identification())
                .userName(request.getUser_name())
                .userEmail(request.getUser_email())
                .user(request.getUser())
                .password(request.getPassword())
                .entryDate((LocalDate.now()))
                .build();
    }

}
