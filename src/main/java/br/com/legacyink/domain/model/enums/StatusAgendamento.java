package br.com.legacyink.domain.model.enums;

import java.util.Arrays;
import java.util.List;

public enum StatusAgendamento {
    AGENDADO("agendado"),
    CONFIRMADO("confirmado", AGENDADO),
    CANCELADO("cancelado", AGENDADO),
    CONCLUIDO("concluido", CONFIRMADO),
    NAO_COMPARECEU("não compareceu", AGENDADO, CONFIRMADO);

    private final String descricao;
    private List<StatusAgendamento> statusAnteriores;

    StatusAgendamento(String descricao, StatusAgendamento... statusAnteriores) {
        this.descricao = descricao;
        this.statusAnteriores = Arrays.asList(statusAnteriores);
    }

    public boolean naoPodeAlterarPara(StatusAgendamento novoStatus) {
        return !novoStatus.statusAnteriores.contains(this);
    }

    public String getDescricao() {
        return descricao;
    }
}