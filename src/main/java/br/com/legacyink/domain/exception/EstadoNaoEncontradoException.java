package br.com.legacyink.domain.exception;

import java.io.Serial;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {
    @Serial
    private static final long serialVersionUID = 1L;

    public EstadoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public EstadoNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de Estado com o código de id: %d", id));
    }
}
