package br.com.legacyink.domain.exception;

import java.io.Serial;

public class ItemNaoEncontradoException extends EntidadeNaoEncontradaException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ItemNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ItemNaoEncontradoException(Long itemId) {
        this(String.format("Não existe um cadastro do item com o código de id: %d", itemId));
    }
}
