package com.legacyInk.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CidadeIdNaoEncontradoException extends NegocioException {

    private static final long serialVersionUID = 1L;

    public CidadeIdNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public CidadeIdNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de usuario com o código de id: %d", id));
    }

}
