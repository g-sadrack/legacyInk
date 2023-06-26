package br.com.legacyink.api.controller;

import br.com.legacyink.api.domainconverter.ItemConvertido;
import br.com.legacyink.api.dto.ItemDTO;
import br.com.legacyink.domain.model.Item;
import br.com.legacyink.domain.service.EstoqueService;
import br.com.legacyink.api.dto.input.ItemInput;
import br.com.legacyink.api.dtoconverter.ItemDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("estudio/{estudioId}/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private ItemDTOConverter converter;

    @Autowired
    private ItemConvertido convertido;

    @GetMapping
    public List<ItemDTO> listar(@PathVariable Long estudioId) {
        List<Item> itens = estoqueService.listar(estudioId);
        return converter.paraDTOLista(itens);
    }

    @GetMapping("/{itemId}")
    public ItemDTO buscar(@PathVariable Long estudioId, @PathVariable Long itemId) {
        Item item = estoqueService.buscaProdutoEmEstoqueDoEstudio(estudioId, itemId);
        return converter.paraDTO(item);
    }

    @PostMapping
    public ItemDTO cadastrar(@PathVariable Long estudioId, @Validated @RequestBody ItemInput itemInput) {
        Item item = convertido.paraModelo(itemInput);
        return converter.paraDTO(estoqueService.associaItemAoEstoqueEstudio(estudioId, item));
    }

    @PutMapping("/{itemId}")
    public ItemDTO alterar(@PathVariable Long itemId, @Validated @RequestBody ItemInput itemInput) {
        Item item = estoqueService.validaProduto(itemId);
        convertido.copiaDTOparaModeloDominio(itemInput, item);
        return converter.paraDTO(estoqueService.salvaItem(itemId));
    }

    @DeleteMapping("/{itemId}")
    public void deleta(@PathVariable Long estudioId, @PathVariable Long itemId) {
        estoqueService.deletar(estudioId, itemId);
    }

}
