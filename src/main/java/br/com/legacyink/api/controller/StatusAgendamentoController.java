package br.com.legacyink.api.controller;

import br.com.legacyink.domain.service.StatusAgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamentos/{agendamentoId}")
public class StatusAgendamentoController {


    private final StatusAgendamentoService statusService;

    @Autowired
    public StatusAgendamentoController(StatusAgendamentoService statusService) {
        this.statusService = statusService;
    }

    @PutMapping("/confirmar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable Long agendamentoId) {
        statusService.confirmar(agendamentoId);
    }

    @PutMapping("/cancelar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Long agendamentoId) {
        statusService.cancelar(agendamentoId);
    }

    @PutMapping("/nao-compareceu")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void naoCompareceu(@PathVariable Long agendamentoId) {
        statusService.naoCompareceu(agendamentoId);
    }

}