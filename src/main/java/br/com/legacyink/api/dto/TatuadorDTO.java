package br.com.legacyink.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TatuadorDTO {
    private Long id;
    private String nome;
    private Integer tempoExperiencia;
    private BigDecimal avaliacao;

}