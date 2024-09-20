package com.tienda.dto;

import com.tienda.Model.TareasModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCreateTask {
    String code ;
    String message;
    TareasModel task;
}
