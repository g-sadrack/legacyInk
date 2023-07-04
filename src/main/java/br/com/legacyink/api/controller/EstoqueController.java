package br.com.legacyink.api.controller;

import br.com.legacyink.api.dto.ItemDTO;
import br.com.legacyink.api.dto.input.ItemInput;
import br.com.legacyink.api.dtoconverter.ItemDTOConverter;
import br.com.legacyink.domain.model.Item;
import br.com.legacyink.domain.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("estudio/{estudioId}/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;
    private final ItemDTOConverter converter;

    @Autowired
    public EstoqueController(EstoqueService estoqueService, ItemDTOConverter converter) {
        this.estoqueService = estoqueService;
        this.converter = converter;
    }

    @GetMapping
    public List<ItemDTO> listarItensDoEstoque(@PathVariable Long estudioId) {
        List<Item> itens = estoqueService.listar(estudioId);
        return converter.paraDTOLista(itens);
    }

    @GetMapping("/{itemId}")
    public ItemDTO buscarItemEmEstoque(@PathVariable Long estudioId, @PathVariable Long itemId) {
        Item item = estoqueService.buscaProdutoEmEstoqueDoEstudio(estudioId, itemId);
        return converter.paraDTO(item);
    }

    @PostMapping
    public ItemDTO cadastrarItemAoEstoque(@PathVariable Long estudioId, @Validated @RequestBody ItemInput itemInput) {
        Item item = estoqueService.associaItemAoEstoqueEstudio(estudioId, itemInput);
        return converter.paraDTO(item);
    }

    @PutMapping("/{itemId}")
    public ItemDTO alterarItemDoEstoque(@PathVariable Long itemId, @Validated @RequestBody ItemInput itemInput) {
        Item item = estoqueService.alterarItem(itemId, itemInput);
        return converter.paraDTO(item);
    }

    @DeleteMapping("/{itemId}")
    public void removerItemDoEstoque(@PathVariable Long estudioId, @PathVariable Long itemId) {
        estoqueService.removerItem(estudioId, itemId);
    }

}
