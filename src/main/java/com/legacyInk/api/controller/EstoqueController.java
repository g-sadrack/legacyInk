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

    @GetMapping("/{estoqueId}")
    public EstoqueDTO buscar(Long estoqueId) {
        Estoque estoque = estoqueService.validaEnderecoOuErro(estoqueId);
        return converter.paraDTO(estoque);
    }

    @PostMapping
    public EstoqueDTO cadastrar(@Validated @RequestBody EstoqueInput estoqueInput) {
        Estoque estoque = convertido.paraModelo(estoqueInput);
        return converter.paraDTO(estoqueService.cadastrar(estoque));
    }

    @PutMapping("/{estoqueId}")
    public EstoqueDTO alterar(@PathVariable Long estoqueId, @Validated @RequestBody Estoque estoqueInput) {
        Estoque estoque = estoqueService.validaEnderecoOuErro(estoqueId);
        convertido.copiaDTOparaModeloDominio(estoqueInput, estoque);
        return converter.paraDTO(estoqueService.cadastrar(estoque));
    }

    @DeleteMapping("/{estoqueId}")
    public void deleta(@PathVariable Long estoqueId) {
        estoqueService.deletar(estoqueId);
    }

}
