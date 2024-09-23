package com.tienda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class RequestCreateTask {
	private String id;
	private String task_name;
	private String delivery_month;

	private String user_id;

}
