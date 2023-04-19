package com.legacyInk.domain.service;

import com.legacyInk.domain.exception.EstudioNaoEncontradoException;
import com.legacyInk.domain.model.Estudio;
import com.legacyInk.domain.repository.EstudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.List;

@Service
public class EstudioService {

    @Autowired
    private EstudioRepository estudioRepository;

    public Estudio buscaEstudioOuErro(Long id) {
        return estudioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Estudio não encontrado"));
    }

    public List<Estudio> listarEstudios() {
        return estudioRepository.findAll();
    }

    @Transient
    public Estudio adicionar(Estudio estudio) {
        return estudioRepository.save(estudio);
    }

    @Transient
    public void remover(Long id) {
        try {
            estudioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EstudioNaoEncontradoException(id);
        }
    }
}