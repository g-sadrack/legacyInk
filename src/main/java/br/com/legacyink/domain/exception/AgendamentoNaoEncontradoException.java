package br.com.legacyink.domain.exception;

import java.io.Serial;

public class AgendamentoNaoEncontradoException extends NegocioException {
    @Serial
    private static final long serialVersionUID = 1L;


    public AgendamentoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public AgendamentoNaoEncontradoException(Long agendamentoId) {
        super(String.format("O Agendamento de id: %d não conta no sistema", agendamentoId));
    }
}
