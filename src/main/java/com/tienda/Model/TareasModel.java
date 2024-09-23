package com.tienda.Model;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(schema="bd_tienda_test", name="TASKS")
public class TareasModel {
	@Id
	@Column(name="ID",unique=true)
	public String id;

	@Column(name="TASK_NAME")
	public String taskName;
	
	@Column(name="DELIVERY_MONTH")
	public String deliveryMonth;

	@OneToOne
	@JoinColumn(name = "USER_ID")
	public UserEntity user;

}
