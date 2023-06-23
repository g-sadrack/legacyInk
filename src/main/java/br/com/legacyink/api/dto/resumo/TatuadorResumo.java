package br.com.legacyink.api.dto.resumo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TatuadorResumo {
    private String nome;
    private Integer tempoExperiencia;
    private BigDecimal avaliacao;

}