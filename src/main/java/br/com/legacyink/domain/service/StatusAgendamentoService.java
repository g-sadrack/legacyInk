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
    public void confirmar(String codigo) {
        Agendamento agendamento = agendamentoService.validaAgendamentoPorCodigoOuErro(codigo);
        agendamento.confirmar();
    }

    @Transactional
    public void cancelar(String codigo) {
        Agendamento agendamento = agendamentoService.validaAgendamentoPorCodigoOuErro(codigo);
        agendamento.cancelar();
    }

    @Transactional
    public void naoCompareceu(String codigo) {
        Agendamento agendamento = agendamentoService.validaAgendamentoPorCodigoOuErro(codigo);
        agendamento.naoCompareceu();
    }
}
