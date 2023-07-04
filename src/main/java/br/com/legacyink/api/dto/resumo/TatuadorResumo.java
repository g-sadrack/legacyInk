package br.com.legacyink.api.dto.resumo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TatuadorResumo {
    private String nome;
    private Integer tempoExperiencia;
    private BigDecimal avaliacao;

}