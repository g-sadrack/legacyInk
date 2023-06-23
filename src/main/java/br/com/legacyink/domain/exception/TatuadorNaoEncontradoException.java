package br.com.legacyink.domain.exception;

import java.io.Serial;

public class TatuadorNaoEncontradoException extends EntidadeNaoEncontradaException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TatuadorNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}
