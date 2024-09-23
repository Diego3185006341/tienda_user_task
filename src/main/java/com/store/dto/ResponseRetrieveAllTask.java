package com.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@AllArgsConstructor
@Data
public class ResponseRetrieveAllTask {

    private String code;
    private String message;
    private List<TaskDtoResponse> retrieve_tasks;

}
