package com.store.dto;

import com.store.Model.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCreateTask {
    String code ;
    String message;
    UUID task_id;
}
