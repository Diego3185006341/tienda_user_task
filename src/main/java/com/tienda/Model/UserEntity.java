package com.tienda.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(schema="bd_tienda_test", name="USERS")
public class UserEntity {
	@Id
	@Column(name="USER_IDENTIFICATION",unique=true)
	public String userIdentification;

	@Column(name="USER_NAME")
	public String userName;
	
	@Column(name="USER_EMAIL")
	public String userEmail;

	@Column(name="USER")
	public String user;
	
	@Column(name="PASSWORD")
	public String password;

	@Column(name="ENTRY_DATE")
	public LocalDate entryDate;
	
	
}
