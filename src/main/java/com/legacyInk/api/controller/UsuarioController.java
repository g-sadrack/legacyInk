package com.legacyInk.api.controller;

import com.legacyInk.domain.model.Usuario;
import com.legacyInk.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{usuarioId}")
    public Usuario listar(@PathVariable Long usuarioId) {
        return usuarioService.validaUsuarioOuErro(usuarioId);
    }

}
