package com.legacyInk.api.controller;

import com.legacyInk.api.domainconverter.EstadoConvertido;
import com.legacyInk.api.dto.EstadoDTO;
import com.legacyInk.api.dto.input.EstadoInput;
import com.legacyInk.api.dtoconverter.EstadoDTOConverter;
import com.legacyInk.domain.model.Estado;
import com.legacyInk.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;
    @Autowired
    private EstadoDTOConverter converter;
    @Autowired
    private EstadoConvertido convertido;


    @GetMapping
    public List<EstadoDTO> listar() {
        List<Estado> listar = estadoService.listar();
        return converter.paraDTOList(listar);
    }

    @GetMapping("/{estadoId}")
    public EstadoDTO busca(@PathVariable Long estadoId) {
        Estado estado = estadoService.validaEstadoOuErro(estadoId);
        return converter.paraDTO(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDTO cadastrar(@Validated @RequestBody EstadoInput estadoInput) {
        Estado estado = convertido.paraModelo(estadoInput);
        return converter.paraDTO(estadoService.salvar(estado));
    }

    @PutMapping("/{estadoId}")
    public EstadoDTO alterar(@PathVariable Long estadoId, @Validated @RequestBody EstadoInput estadoInput) {
        Estado estado = estadoService.validaEstadoOuErro(estadoId);
        convertido.copiaDTOparaModeloDominio(estadoInput, estado);
        return converter.paraDTO(estadoService.salvar(estado));
    }

    @DeleteMapping("/{estadoId}")
    public void remover(@PathVariable Long estadoId) {
        estadoService.removeEstado(estadoId);
    }

}