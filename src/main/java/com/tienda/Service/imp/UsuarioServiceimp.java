package com.tienda.Service.imp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.tienda.Service.IUserService;
import com.tienda.Model.UserEntity;
import com.tienda.Repository.UserRepository;
import com.tienda.dto.FiltroDetalle;

import com.tienda.dto.FiltrosDto;
import com.tienda.dto.RequestCreateUser;
import com.tienda.dto.ResponseMessage;


import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UsuarioServiceimp implements IUserService {


    @Autowired
    private UserRepository usuarioR;


    @Override
    public ResponseEntity<List<UserEntity>> retrieveAllUsers() {

        try {

            List<UserEntity> user = new ArrayList<>(usuarioR.findAll());
            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*/@Override
    public Optional<UsuarioModel> listarIdUsuario(String Cedula) {
        return usuariodb.findById(Cedula);
    }

    @Override
    public int saveUsuario (UsuarioModel u) {
        // TODO Auto-generated method stub
        int res =0;
        UsuarioModel usuario = usuariodb.save(u);
        if(!usuario.equals(null)) {
            res=1;
        }
        return res;
    }
*/
    @Override
    public ResponseEntity<ResponseMessage> deleteUser(String Cedula) {

        try {
            ResponseMessage response = new ResponseMessage();
            usuarioR.deleteById(Cedula);
            response.setMessage("se elimino usuario");
            return new ResponseEntity<>(response, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @Override
    public ResponseEntity<Object> saveUser(RequestCreateUser request) {
        try {
            Optional<UserEntity> u = usuarioR.findById(request.getCedula_Usuario());

            if (u.isPresent()) {

                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

            } else {
                usuarioR.save(UserEntity.builder().userIdentification(request.getCedula_Usuario()).userName(request.getNombre_Usuario())
                        .userEmail(request.getCorreo_Usuario()).user(request.getUsuario()).password(request.getClave_Usuario()).
                        entryDate((LocalDate.now())).build());

                return new ResponseEntity<>(request, HttpStatus.CREATED);


            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Object> updateUser(String id, RequestCreateUser request) {
        try {
            Optional<UserEntity> u = usuarioR.findById(id);

            if (u.isEmpty()) {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                UserEntity user = u.get();
                user.setUserIdentification(request.getCedula_Usuario());
                user.setPassword(request.getClave_Usuario());
                user.setUserEmail(request.getCorreo_Usuario());
                user.setUserName(request.getNombre_Usuario());
                user.setUser(request.getUsuario());


                return new ResponseEntity<>(usuarioR.save(user), HttpStatus.OK);


            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<RequestCreateUser> findUserByIdentification(String id) {

        try {
            Optional<UserEntity> u = usuarioR.findById(id);

            if (u.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                UserEntity user = u.get();
                RequestCreateUser respuesta = new RequestCreateUser();
                respuesta.setCedula_Usuario(user.getUserIdentification());
                respuesta.setClave_Usuario(user.getPassword());
                respuesta.setCorreo_Usuario(user.getUserEmail());
                respuesta.setNombre_Usuario(user.getUserName());
                respuesta.setUsuario(user.getUser());
                respuesta.setFecha_Ingreso(user.getEntryDate());
                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


}