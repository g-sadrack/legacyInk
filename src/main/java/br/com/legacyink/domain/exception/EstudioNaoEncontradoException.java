package br.com.legacyink.domain.exception;

import java.io.Serial;

public class EstudioNaoEncontradoException extends EntidadeNaoEncontradaException {
    @Serial
    private static final long serialVersionUID = 1L;

    public EstudioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public EstudioNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de estudio com o código de id: %d", id));
    }
}
