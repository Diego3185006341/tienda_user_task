package com.store.Controlador;

import java.util.List;

import com.store.dto.ResponseCreateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.store.Service.IUserService;
import com.store.Model.UserEntity;
import com.store.dto.RequestCreateUser;
import com.store.dto.ResponseMessage;





@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private IUserService iUserService;

	
	@GetMapping("/retrieveAllUsers")
	public ResponseEntity<List<UserEntity>> retrieveAllUsers()
	{
		return iUserService.retrieveAllUsers();
	}
	

	@DeleteMapping ("deleteUser/{id}")
	public ResponseEntity<ResponseMessage> deleteUser(@PathVariable String id)
	{
		return iUserService.deleteUser(id);
	}
	@PostMapping("/saveUser")
	public ResponseEntity<ResponseCreateUser> saveUser(@RequestBody RequestCreateUser request) {
		
		return iUserService.saveUser(request);
	}	

	@PutMapping("/updateUser/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable String id, @RequestBody RequestCreateUser request) {
		
		return iUserService.updateUser(id,request );
	}
	

	@GetMapping ("/findUserByIdentification/{id}")
	public ResponseEntity<RequestCreateUser> findUserByIdentification(@PathVariable String id)
	{
		return  iUserService.findUserByIdentification(id);
	}

}
