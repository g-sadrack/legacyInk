package com.legacyInk.domain.service;

import com.legacyInk.domain.exception.EntidadeNaoEncontradaException;
import com.legacyInk.domain.model.Estoque;
import com.legacyInk.domain.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public static final String MSG_AGENDAMENTO_NAO_ENCONTRADO = "O item de ID %d , não consta no sistema";


    public Estoque validaEnderecoOuErro(Long estoqueId) {
        return estoqueRepository.findById(estoqueId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_AGENDAMENTO_NAO_ENCONTRADO, estoqueId)));
    }


    public List<Estoque> listar() {
        return estoqueRepository.findAll();
    }

    @Transactional
    public Estoque cadastrar(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    @Transactional
    public void deletar(Long estoqueId) {
        estoqueRepository.deleteById(estoqueId);
    }

}
