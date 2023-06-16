package br.com.legacyink.domain.exception;

import java.io.Serial;

public class EntidadeEmUsoException extends NegocioException {
    @Serial
    private static final long serialVersionUID = 1L;

    public EntidadeEmUsoException(String mensagem) {
        super(mensagem);
    }

}
