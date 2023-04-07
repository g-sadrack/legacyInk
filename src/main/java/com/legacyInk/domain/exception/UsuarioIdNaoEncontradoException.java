package com.legacyInk.domain.exception;

public class UsuarioIdNaoEncontradoException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public UsuarioIdNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioIdNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de usuario com o código de id: %d", id));
    }
}
