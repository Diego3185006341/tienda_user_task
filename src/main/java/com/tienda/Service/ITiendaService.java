package com.tienda.Service;

import com.tienda.Model.UsuarioModel;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ITiendaService {

    UsuarioModel getUsuario(String cedula);
}
