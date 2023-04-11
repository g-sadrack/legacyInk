package com.legacyInk.domain.service;

import com.legacyInk.domain.exception.CidadeIdNaoEncontradoException;
import com.legacyInk.domain.exception.EntidadeNaoEncontradaException;
import com.legacyInk.domain.model.Cidade;
import com.legacyInk.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CidadeService {

    public static final String MSG_CIDADE_NAO_CONSTA_NO_SISTEMA = "A cidade de ID %d , não consta no sistema";
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoService estadoService;

    public Cidade validaEnderecoOuErro(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new CidadeIdNaoEncontradoException(
                        String.format(MSG_CIDADE_NAO_CONSTA_NO_SISTEMA, cidadeId)));
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @Transactional
    public Cidade cadastrar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        estadoService.validaEstadoOuErro(estadoId);
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void deleta(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeIdNaoEncontradoException(String.format(MSG_CIDADE_NAO_CONSTA_NO_SISTEMA, cidadeId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_CONSTA_NO_SISTEMA, cidadeId));
        }
    }
}
