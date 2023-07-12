package br.com.legacyink.domain.service;

import br.com.legacyink.domain.exception.NegocioException;
import br.com.legacyink.domain.model.Agendamento;
import br.com.legacyink.domain.model.enums.StatusAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class StatusAgendamentoService {

    public static final String STATUS_DO_PEDIDO_NAO_PODE_SER_ALTERADO = "O status do pedido %d não pode ser alterado de %s para %s";
    private final AgendamentoService agendamentoService;

    @Autowired
    public StatusAgendamentoService(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }


    @Transactional
    public void confirmar(Long agendamentoId) {
        Agendamento agendamento = agendamentoService.validaAgendamentoOuErro(agendamentoId);
        if (!agendamento.getStatus().equals(StatusAgendamento.AGENDADO)) {
            throw new NegocioException(
                    String.format(STATUS_DO_PEDIDO_NAO_PODE_SER_ALTERADO,
                            agendamento.getId(), agendamento.getStatus().getDescricao(),
                            StatusAgendamento.COFIRMADO));
        }
        agendamento.setStatus(StatusAgendamento.COFIRMADO);
        agendamento.setDataHora(LocalDateTime.now());
    }

    @Transactional
    public void cancelar(Long agendamentoId) {
        Agendamento agendamento = agendamentoService.validaAgendamentoOuErro(agendamentoId);
        if (agendamento.getStatus().equals(StatusAgendamento.COFIRMADO) || agendamento.getStatus().equals(StatusAgendamento.CONCLUIDO) || agendamento.getStatus().equals(StatusAgendamento.NAO_COMPARECEU)) {
            throw new NegocioException(
                    String.format(STATUS_DO_PEDIDO_NAO_PODE_SER_ALTERADO,
                            agendamento.getId(), agendamento.getStatus().getDescricao(),
                            StatusAgendamento.CANCELADO));
        }
        agendamento.setStatus(StatusAgendamento.CANCELADO);
        agendamento.setDataHora(LocalDateTime.now());

    }

    @Transactional
    public void naoCompareceu(Long agendamentoId) {
        Agendamento agendamento = agendamentoService.validaAgendamentoOuErro(agendamentoId);
        if (!(agendamento.getStatus().equals(StatusAgendamento.AGENDADO) || agendamento.getStatus().equals(StatusAgendamento.COFIRMADO))) {
            throw new NegocioException(
                    String.format(STATUS_DO_PEDIDO_NAO_PODE_SER_ALTERADO,
                            agendamento.getId(), agendamento.getStatus().getDescricao(),
                            StatusAgendamento.NAO_COMPARECEU));
        }
        agendamento.setStatus(StatusAgendamento.NAO_COMPARECEU);
        agendamento.setDataHora(LocalDateTime.now());
    }
}
