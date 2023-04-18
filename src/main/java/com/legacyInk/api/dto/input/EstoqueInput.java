package com.legacyInk.api.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstoqueInput {
    private String nome;
    private String descricao;
    private Integer quantidade;

}