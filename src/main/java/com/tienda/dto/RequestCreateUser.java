package com.tienda.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class RequestCreateUser {
	private String user_identification;
	private String user_name;
	private String user_email;
	private String user;
	private String password;
	private LocalDate entry_date;
	

}
