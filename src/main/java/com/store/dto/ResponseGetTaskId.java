package com.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetTaskId {

	
	private String code;
	private String message;
	private UUID task_id;
	private String task_name;
	private String user_id;


}
