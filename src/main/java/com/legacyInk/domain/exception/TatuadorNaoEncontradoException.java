package com.legacyInk.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TatuadorNaoEncontradoException extends NegocioException {

    private static final long serialVersionUID = 1L;

    public TatuadorNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}
