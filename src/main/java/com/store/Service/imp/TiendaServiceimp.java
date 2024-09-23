package com.store.Service.imp;

import com.store.Model.UserEntity;
import com.store.Repository.UserRepository;
import com.store.Service.ITiendaService;
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

        if (usuarioEntity.isEmpty()){
           throw new RuntimeException("not user found for this cedula");
        }

        return usuarioEntity.get();
    }
}
