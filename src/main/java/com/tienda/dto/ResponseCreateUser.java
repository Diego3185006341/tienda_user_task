package com.tienda.dto;

import com.tienda.Model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCreateUser {
    String code ;
    String message;
    UserEntity user;
}
