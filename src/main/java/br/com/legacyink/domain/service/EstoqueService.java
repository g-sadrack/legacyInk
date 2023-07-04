package br.com.legacyink.domain.service;

import br.com.legacyink.api.domainconverter.ItemConvertido;
import br.com.legacyink.api.dto.input.ItemInput;
import br.com.legacyink.domain.exception.ItemNaoEncontradoException;
import br.com.legacyink.domain.model.Estudio;
import br.com.legacyink.domain.model.Item;
import br.com.legacyink.domain.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstoqueService {

    public static final String MSG_ITEM_NAO_ENCONTRADO_EM_ESTOQUE = "O item de ID %d , não consta no estoque";

    private final EstoqueRepository estoqueRepository;
    private final EstudioService estudioService;
    private final ItemConvertido convertido;

    @Autowired
    public EstoqueService(EstoqueRepository estoqueRepository, EstudioService estudioService, ItemConvertido convertido) {
        this.estoqueRepository = estoqueRepository;
        this.estudioService = estudioService;
        this.convertido = convertido;
    }

    public Item validaProduto(Long itemId) {
        return estoqueRepository.findById(itemId).orElseThrow(
                () -> new ItemNaoEncontradoException(itemId));
    }

    public Item buscaProdutoEmEstoqueDoEstudio(Long estudioId, Long itemId) {
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);
        return estudio.getEstoque().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ItemNaoEncontradoException(itemId));
    }

    public List<Item> listar(Long estudioId) {
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);
        return new ArrayList<>(estudio.getEstoque());
    }

    @Transactional
    public Item alterarItem(Long itemId, ItemInput itemInput) {
        Item item = this.validaProduto(itemId);

        convertido.copiaDTOparaModeloDominio(itemInput, item);
        return estoqueRepository.save(item);
    }

    @Transactional
    public Item associaItemAoEstoqueEstudio(Long estudioId, ItemInput itemInput) {
        Item item = convertido.paraModelo(itemInput);
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);

        estoqueRepository.save(item);
        estudio.adicionarItem(item);
        return item;
    }

    @Transactional
    public void removerItem(Long estudioId, Long estoqueId) {
        try {
            Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);
            Item item = validaProduto(estoqueId);
            estudio.removerItem(item);
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNaoEncontradoException(String.format(MSG_ITEM_NAO_ENCONTRADO_EM_ESTOQUE, estoqueId));
        }
    }
}
