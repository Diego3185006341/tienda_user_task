package com.store.Utils;

import com.store.Model.TaskEntity;
import com.store.Model.UserEntity;
import com.store.dto.*;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class Mappers {

    public static TaskEntity getBuildTask(RequestCreateTask request) {
        return TaskEntity.builder()
                .taskName(request.getTask_name())
                .deliveryMonth(request.getDelivery_month())
                .userID(request.getUser_id())
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

    public static TaskDtoResponse getBuildResponseRetrieveAll(TaskEntity task) {
        return TaskDtoResponse.builder()
                .task_id(task.getId())
                .task_name(task.getTaskName())
                .user_id(task.getUserID())
                .build();
    }

}
