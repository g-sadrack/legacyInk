package com.legacyInk.domain.exception;

public class EstudioNaoEncontradoException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public EstudioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public EstudioNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de estudio com o código de id: %d", id));
    }
}
