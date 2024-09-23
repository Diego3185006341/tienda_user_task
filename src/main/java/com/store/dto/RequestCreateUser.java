package com.store.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class RequestCreateUser {
	@JsonProperty("user_identification")
	private String user_identification;
	@JsonProperty("user_name")
	private String user_name;
	@JsonProperty("user_email")
	private String user_email;
	@JsonProperty("user")
	private String user;
	@JsonProperty("password")
	private String password;
	@JsonProperty("entry_date")
	private LocalDate entry_date;
	

}
