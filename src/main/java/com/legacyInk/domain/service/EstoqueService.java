package com.legacyInk.domain.service;

import com.legacyInk.domain.exception.EntidadeEmUsoException;
import com.legacyInk.domain.exception.ItemNaoEncontradoException;
import com.legacyInk.domain.model.Estoque;
import com.legacyInk.domain.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public static final String MSG_ITEM_NAO_ENCONTRADO_EM_ESTOQUE = "O item de ID %d , não consta no estoque";

    public Estoque encontraItemOuErro(Long itemId) {
        return estoqueRepository.findById(itemId)
                .orElseThrow(() -> new ItemNaoEncontradoException(
                        String.format(MSG_ITEM_NAO_ENCONTRADO_EM_ESTOQUE, itemId)));
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
        try {
            estoqueRepository.deleteById(estoqueId);
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNaoEncontradoException(
                    String.format(MSG_ITEM_NAO_ENCONTRADO_EM_ESTOQUE, estoqueId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ITEM_NAO_ENCONTRADO_EM_ESTOQUE, estoqueId));
        }
    }

}
