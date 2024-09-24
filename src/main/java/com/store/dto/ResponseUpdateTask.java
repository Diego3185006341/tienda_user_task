package com.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data

public class ResponseUpdateTask {

    private String code;
    private String message;
    private TaskDtoResponse task;
}
