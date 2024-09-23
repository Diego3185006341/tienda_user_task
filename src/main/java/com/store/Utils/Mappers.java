package com.store.Utils;

import com.store.Model.TaskEntity;
import com.store.Model.UserEntity;
import com.store.dto.RequestCreateTask;
import com.store.dto.RequestCreateUser;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class Mappers {

    public static TaskEntity getBuildTask(RequestCreateTask request, UserEntity user) {
        return TaskEntity.builder()
                .taskName(request.getTask_name())
                .deliveryMonth(request.getDelivery_month())
                .userID(user.getUserIdentification())
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
