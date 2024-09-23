package com.store.Service;

import java.util.List;


import com.store.dto.ResponseCreateUser;
import org.springframework.http.ResponseEntity;


import com.store.Model.UserEntity;
import com.store.dto.RequestCreateUser;
import com.store.dto.ResponseMessage;

public interface IUserService {


	 ResponseEntity<List<UserEntity>> retrieveAllUsers();

	ResponseEntity<ResponseMessage> deleteUser(String id);

	 ResponseEntity<ResponseCreateUser> saveUser(RequestCreateUser request);

	 ResponseEntity<Object> updateUser(String id, RequestCreateUser request);
	 ResponseEntity<RequestCreateUser> findUserByIdentification(String id);

	
}
