package com.tienda.Service.imp;

import com.tienda.Model.UserEntity;
import com.tienda.Repository.UserRepository;
import com.tienda.Service.ITiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TiendaServiceimp implements ITiendaService {

    public final UserRepository usuarioR;

    @Override
    public UserEntity getUsuario(String cedula) {
        Optional<UserEntity> usuarioEntity = usuarioR.findById(cedula);

        if (!usuarioEntity.isPresent()){
           throw new RuntimeException("not user found for this cedula");
        }

        return usuarioEntity.get();
    }
}
