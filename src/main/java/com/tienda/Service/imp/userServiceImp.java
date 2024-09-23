package com.tienda.Service.imp;

import com.tienda.Model.UserEntity;
import com.tienda.Repository.UserRepository;
import com.tienda.Service.IUserService;
import com.tienda.Utils.Mappers;
import com.tienda.dto.RequestCreateUser;
import com.tienda.dto.ResponseCreateTask;
import com.tienda.dto.ResponseCreateUser;
import com.tienda.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class userServiceImp implements IUserService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseEntity<List<UserEntity>> retrieveAllUsers() {

        try {

            List<UserEntity> user = new ArrayList<>(userRepository.findAll());
            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<ResponseMessage> deleteUser(String id) {

        try {
            ResponseMessage response = new ResponseMessage();
            userRepository.deleteById(id);
            response.setMessage("se elimino usuario");
            return new ResponseEntity<>(response, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @Override
    public ResponseEntity<ResponseCreateUser> saveUser(RequestCreateUser request) {
        try {
             userRepository.findById(request.getUser_identification()).ifPresent( userEntity ->{
                throw new IllegalArgumentException("user with this id" + request.getUser_identification().concat("already exits"));
            });
                userRepository.save(Mappers.getBuildUser(request));
                return new ResponseEntity<>(ResponseCreateUser
                        .builder()
                        .code("200")
                        .message("OK")
                        .user(Mappers.getBuildUser(request))
                        .build(), HttpStatus.CREATED);
        }	catch (Exception e) {
            return new ResponseEntity<>(ResponseCreateUser.
                    builder()
                    .code("400")
                    .message(e.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }

    }



    @Override
    public ResponseEntity<Object> updateUser(String id, RequestCreateUser request) {
        try {
            Optional<UserEntity> u = userRepository.findById(id);

            if (u.isEmpty()) {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                UserEntity user = u.get();
                user.setUserIdentification(request.getUser_identification());
                user.setPassword(request.getPassword());
                user.setUserEmail(request.getUser_email());
                user.setUserName(request.getUser_name());
                user.setUser(request.getUser());


                return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);


            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<RequestCreateUser> findUserByIdentification(String id) {

        try {
            Optional<UserEntity> u = userRepository.findById(id);

            if (u.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                UserEntity user = u.get();
                RequestCreateUser response = new RequestCreateUser();
                response.setUser_identification(user.getUserIdentification());
                response.setPassword(user.getPassword());
                response.setUser_email(user.getUserEmail());
                response.setUser_name(user.getUserName());
                response.setUser(user.getUser());
                response.setEntry_date(user.getEntryDate());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


}