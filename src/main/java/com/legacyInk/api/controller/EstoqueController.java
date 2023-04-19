package com.legacyInk.api.controller;

import com.legacyInk.api.domainconverter.EstoqueConvertido;
import com.legacyInk.api.dto.EstoqueDTO;
import com.legacyInk.api.dto.input.EstoqueInput;
import com.legacyInk.api.dtoconverter.EstoqueDTOConverter;
import com.legacyInk.domain.model.Estoque;
import com.legacyInk.domain.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("estudio/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private EstoqueDTOConverter converter;

    @Autowired
    private EstoqueConvertido convertido;

    @GetMapping
    public List<EstoqueDTO> listar() {
        List<Estoque> estoques = estoqueService.listar();
        return converter.paraDTOLista(estoques);
    }

    @GetMapping("/{itemId}")
    public EstoqueDTO buscar(@PathVariable Long itemId) {
        Estoque estoque = estoqueService.encontraItemOuErro(itemId);
        return converter.paraDTO(estoque);
    }

    @PostMapping
    public EstoqueDTO cadastrar(@Validated @RequestBody EstoqueInput itemInput) {
        Estoque estoque = convertido.paraModelo(itemInput);
        return converter.paraDTO(estoqueService.cadastrar(estoque));
    }

    @PutMapping("/{itemId}")
    public EstoqueDTO alterar(@PathVariable Long itemId, @Validated @RequestBody EstoqueInput itemInput) {
        Estoque estoque = estoqueService.encontraItemOuErro(itemId);
        convertido.copiaDTOparaModeloDominio(itemInput, estoque);
        return converter.paraDTO(estoqueService.cadastrar(estoque));
    }

    @DeleteMapping("/{itemId}")
    public void deleta(@PathVariable Long itemId) {
        estoqueService.deletar(itemId);
    }

}
