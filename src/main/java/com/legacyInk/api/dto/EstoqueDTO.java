package com.legacyInk.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstoqueDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Integer quantidade;

}