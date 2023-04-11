package com.legacyInk.domain.service;

import com.legacyInk.domain.exception.EstadoIdNaoEncontradoException;
import com.legacyInk.domain.model.Estado;
import com.legacyInk.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EstadoService {
    public static final String MSG_ESTADO_ID_NAO_CONSTA_NO_SISTEMA = "O estado ID %d , não consta no sistema";
    @Autowired
    private EstadoRepository estadoRepository;

    public Estado validaEstadoOuErro(Long estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoIdNaoEncontradoException(
                        String.format(MSG_ESTADO_ID_NAO_CONSTA_NO_SISTEMA, estadoId)));
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public void removeEstado(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
            estadoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoIdNaoEncontradoException(String.format(MSG_ESTADO_ID_NAO_CONSTA_NO_SISTEMA, estadoId));
        }
    }
}
