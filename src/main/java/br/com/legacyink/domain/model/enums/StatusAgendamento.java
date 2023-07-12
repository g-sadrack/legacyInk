package br.com.legacyink.domain.model.enums;

public enum StatusAgendamento {
    AGENDADO("agendado"),
    COFIRMADO("confirmado"),
    CANCELADO("cancelado"),
    CONCLUIDO("concluido"),
    NAO_COMPARECEU("não compareceu");

    private final String descricao;

    StatusAgendamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}