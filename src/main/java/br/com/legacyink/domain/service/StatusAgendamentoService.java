package br.com.legacyink.domain.service;

import br.com.legacyink.domain.model.Agendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StatusAgendamentoService {

    private final AgendamentoService agendamentoService;

    @Autowired
    public StatusAgendamentoService(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @Transactional
    public void confirmar(Long agendamentoId) {
        Agendamento agendamento = agendamentoService.validaAgendamentoOuErro(agendamentoId);
        agendamento.confirmar();
    }

    @Transactional
    public void cancelar(Long agendamentoId) {
        Agendamento agendamento = agendamentoService.validaAgendamentoOuErro(agendamentoId);
        agendamento.cancelar();
    }

    @Transactional
    public void naoCompareceu(Long agendamentoId) {
        Agendamento agendamento = agendamentoService.validaAgendamentoOuErro(agendamentoId);
        agendamento.naoCompareceu();
    }
}
