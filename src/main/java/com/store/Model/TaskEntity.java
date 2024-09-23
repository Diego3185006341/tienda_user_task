package com.store.Model;


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
public class TaskEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID",unique=true)
	public Integer id;

	@Column(name="TASK_NAME")
	public String taskName;
	
	@Column(name="DELIVERY_MONTH")
	public String deliveryMonth;

	@OneToOne
	@JoinColumn(name = "USER_ID", nullable = false, insertable = false, updatable = false)
	public UserEntity user;


	@Column(name = "USER_ID")
	public String userID;

}
