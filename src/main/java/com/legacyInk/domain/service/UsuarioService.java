package com.legacyInk.domain.service;

import com.legacyInk.domain.exception.UsuarioIdNaoEncontradoException;
import com.legacyInk.domain.model.Usuario;
import com.legacyInk.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario validaUsuarioOuErro(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new UsuarioIdNaoEncontradoException(String.format("O usuário de ID %d , não consta no sistema", usuarioId)));
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

}
