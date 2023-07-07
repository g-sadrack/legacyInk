package br.com.legacyink.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TatuadorDTO {
    private Long id;
    private String nome;
    private Integer tempoExperiencia;
    private BigDecimal avaliacao;
    private Boolean ativo;
}