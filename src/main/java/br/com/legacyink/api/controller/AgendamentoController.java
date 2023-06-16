package br.com.legacyink.api.controller;

import br.com.legacyink.api.dtoconverter.AgendamentoDTOConverter;
import br.com.legacyink.domain.model.Agendamento;
import br.com.legacyink.api.domainconverter.AgendamentoConvertido;
import br.com.legacyink.api.dto.AgendamentoDTO;
import br.com.legacyink.api.dto.input.AgendamentoInput;
import br.com.legacyink.domain.service.AgendamentoService;
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
    private AgendamentoConvertido convertido;

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