package com.legacyInk.domain.exception;

public class AgendamentoNaoEncontradoException extends NegocioException {
    private static final long serialVersionUID = 1L;


    public AgendamentoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public AgendamentoNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
