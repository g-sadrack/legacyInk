package com.legacyInk.domain.exception;

public class EstadoIdNaoEncontradoException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public EstadoIdNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public EstadoIdNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de Estado com o código de id: %d", id));
    }
}
