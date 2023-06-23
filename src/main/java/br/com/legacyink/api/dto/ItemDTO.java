package br.com.legacyink.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Integer quantidade;

}
