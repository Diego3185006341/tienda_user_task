package com.tienda.Service.imp;

import com.tienda.Model.UsuarioModel;
import com.tienda.Repository.UsuarioRepository;
import com.tienda.Service.ITiendaService;
import com.tienda.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TiendaServiceimp implements ITiendaService {

    public final UsuarioRepository usuarioR;

    @Override
    public UsuarioModel getUsuario(String cedula) {
        Optional<UsuarioModel> usuarioEntity = usuarioR.findById(cedula);

        if (!usuarioEntity.isPresent()){
           throw new RuntimeException("not user found for this cedula");
        }

        return usuarioEntity.get();
    }
}
