package com.legacyInk.domain.exception;

public class ClienteIdNaoEncontradoException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public ClienteIdNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ClienteIdNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de usuario com o código de id: %d", id));
    }
}
