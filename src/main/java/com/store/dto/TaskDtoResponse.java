package com.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Data
public class TaskDtoResponse {

    private UUID task_id;
    private String task_name;
    private String user_id;
}
