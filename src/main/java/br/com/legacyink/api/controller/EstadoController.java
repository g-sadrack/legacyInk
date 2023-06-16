package br.com.legacyink.api.controller;

import br.com.legacyink.api.domainconverter.EstadoConvertido;
import br.com.legacyink.api.dto.EstadoDTO;
import br.com.legacyink.domain.model.Estado;
import br.com.legacyink.api.dto.input.EstadoInput;
import br.com.legacyink.api.dtoconverter.EstadoDTOConverter;
import br.com.legacyink.domain.service.EstadoService;
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