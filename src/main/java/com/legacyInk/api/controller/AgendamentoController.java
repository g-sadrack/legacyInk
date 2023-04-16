package com.legacyInk.api.controller;

import com.legacyInk.api.domainconverter.AgendamentoDTOConvertido;
import com.legacyInk.api.dto.AgendamentoDTO;
import com.legacyInk.api.dto.input.AgendamentoInput;
import com.legacyInk.api.dtoconverter.AgendamentoDTOConverter;
import com.legacyInk.domain.model.Agendamento;
import com.legacyInk.domain.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;
    @Autowired
    private AgendamentoDTOConverter converter;
    @Autowired
    private AgendamentoDTOConvertido convertido;

    @GetMapping
    public List<AgendamentoDTO> listar() {
        List<Agendamento> listar = agendamentoService.listar();
        return converter.paraDTOLista(listar);
    }

    @GetMapping("/{agendamentoId}")
    public AgendamentoDTO buscar(@PathVariable Long agendamentoId) {
        var agendamento = agendamentoService.validaEnderecoOuErro(agendamentoId);
        return converter.paraDTO(agendamento);
    }

    @PostMapping
    public AgendamentoDTO realizar(@Validated @RequestBody AgendamentoInput agendamentoInput) {
        Agendamento agendamento = convertido.paraModelo(agendamentoInput);
        return converter.paraDTO(agendamentoService.cadastrar(agendamento));
    }

    @PutMapping("/{agendamentoId}")
    public AgendamentoDTO alterar(@PathVariable Long agendamentoId, @Validated @RequestBody AgendamentoInput agendamentoInput) {
        Agendamento agendamento = agendamentoService.validaEnderecoOuErro(agendamentoId);
        convertido.copiaDTOparaModeloDominio(agendamentoInput, agendamento);
        return converter.paraDTO(agendamentoService.cadastrar(agendamento));
    }

    @DeleteMapping("/{agendamentoId}")
    public void deletar(@PathVariable Long agendamentoId) {
        agendamentoService.deleta(agendamentoId);
    }

}