package br.com.legacyink.api.controller;

import br.com.legacyink.api.dto.AgendamentoDTO;
import br.com.legacyink.api.dto.input.AgendamentoInput;
import br.com.legacyink.api.dtoconverter.AgendamentoDTOConverter;
import br.com.legacyink.domain.model.Agendamento;
import br.com.legacyink.domain.repository.filter.AgendamentoFilter;
import br.com.legacyink.domain.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("estudios/{estudioId}/tatuador/{tatuadorId}/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final AgendamentoDTOConverter converter;

    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService, AgendamentoDTOConverter converter) {
        this.agendamentoService = agendamentoService;
        this.converter = converter;
    }

    @GetMapping
    public List<AgendamentoDTO> pesquisarAgendamentosComFiltro(AgendamentoFilter filtro) {
        List<Agendamento> listar = agendamentoService.pesquisar(filtro);
        return converter.paraDTOLista(listar);
    }

    @GetMapping("/{codigo}")
    public AgendamentoDTO buscarPorCodigo(@PathVariable String codigo) {
        Agendamento agendamento = agendamentoService.validaAgendamentoPorCodigoOuErro(codigo);
        return converter.paraDTO(agendamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoDTO agendar(@PathVariable Long estudioId,
                                  @PathVariable Long tatuadorId, @Validated @RequestBody AgendamentoInput agendamentoInput) {
        Agendamento agendamento = agendamentoService.agendar(estudioId, tatuadorId, agendamentoInput);
        return converter.paraDTO(agendamento);
    }

    @PutMapping("/{agendamentoId}")
    public AgendamentoDTO alterar(@PathVariable Long estudioId, @PathVariable Long tatuadorId,
                                  @PathVariable Long agendamentoId, @Validated @RequestBody AgendamentoInput agendamentoInput) {
        Agendamento agendamento = agendamentoService.alterarAgendamento(estudioId, tatuadorId, agendamentoId, agendamentoInput);
        return converter.paraDTO(agendamento);
    }

    @DeleteMapping("/{agendamentoId}")
    public void desagendar(@PathVariable Long estudioId,
                           @PathVariable Long tatuadorId, @PathVariable Long agendamentoId) {
        agendamentoService.desagendar(estudioId, tatuadorId, agendamentoId);
    }

}