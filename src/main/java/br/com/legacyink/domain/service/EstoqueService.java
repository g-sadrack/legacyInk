package br.com.legacyink.domain.service;

import br.com.legacyink.domain.exception.EntidadeEmUsoException;
import br.com.legacyink.domain.exception.ItemNaoEncontradoException;
import br.com.legacyink.domain.model.Estudio;
import br.com.legacyink.domain.model.Item;
import br.com.legacyink.domain.repository.EstoqueRepository;
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

    @Autowired
    private EstudioService estudioService;

    public static final String MSG_ITEM_NAO_ENCONTRADO_EM_ESTOQUE = "O item de ID %d , não consta no estoque";

    @Transactional
    public Item salvaItem(Long itemId) {
        Item item = this.validaProduto(itemId);
        return estoqueRepository.save(item);
    }

    @Transactional
    public void deletar (Long estudioId, Long estoqueId) {
        try {
            Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);
            Item item = validaProduto(estoqueId);
            estudio.removerItem(item);
            estoqueRepository.deleteById(estoqueId);
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNaoEncontradoException(String.format(MSG_ITEM_NAO_ENCONTRADO_EM_ESTOQUE, estoqueId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ITEM_NAO_ENCONTRADO_EM_ESTOQUE, estoqueId));
        }
    }

    public Item buscaProduto(Long estudioId, Long itemId) {
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);
        for (Item item : estudio.getEstoque()) {
            if (item.getId().equals(itemId)) {
                return item;
            }
        }
        throw new ItemNaoEncontradoException(String.format(MSG_ITEM_NAO_ENCONTRADO_EM_ESTOQUE, itemId));
    }

    @Transactional
    public Item associaItemAoEstoqueEstudio(Long estudioId, Item item) {
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);
        estoqueRepository.save(item);
        estudio.adicionarItem(item);
        return item;
    }

    public List<Item> listar(Long estudioId) {
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);
        return estudio.getEstoque();
    }

    public Item validaProduto(Long itemId) {
        return estoqueRepository.findById(itemId).orElseThrow(() -> new ItemNaoEncontradoException(String.format(MSG_ITEM_NAO_ENCONTRADO_EM_ESTOQUE, itemId)));
    }

}
