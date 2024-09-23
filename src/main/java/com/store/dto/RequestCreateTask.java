package com.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class RequestCreateTask {
	@JsonProperty("task_name")
	private String task_name;
	@JsonProperty("delivery_month")
	private String delivery_month;
	@JsonProperty("user_id")
	private String user_id;

}
