package br.com.legacyink.domain.service;

import br.com.legacyink.domain.exception.EstadoNaoEncontradoException;
import br.com.legacyink.domain.model.Estado;
import br.com.legacyink.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EstadoService {
    public static final String MSG_ESTADO_ID_NAO_CONSTA_NO_SISTEMA = "O estado ID %d , não consta no sistema";
    private final EstadoRepository estadoRepository;

    @Autowired
    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public Estado validaEstadoOuErro(Long estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(
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
            throw new EstadoNaoEncontradoException(String.format(MSG_ESTADO_ID_NAO_CONSTA_NO_SISTEMA, estadoId));
        }
    }
}
