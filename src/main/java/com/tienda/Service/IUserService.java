package com.tienda.Service;

import java.util.List;


import org.springframework.http.ResponseEntity;


import com.tienda.Model.UserEntity;
import com.tienda.dto.RequestCreateUser;
import com.tienda.dto.ResponseMessage;

public interface IUserService {


	 ResponseEntity<List<UserEntity>> retrieveAllUsers();

	ResponseEntity<ResponseMessage> deleteUser(String id);

	 ResponseEntity<Object> saveUser(RequestCreateUser request);

	 ResponseEntity<Object> updateUser(String id, RequestCreateUser request);
	 ResponseEntity<RequestCreateUser> findUserByIdentification(String id);

	
}
