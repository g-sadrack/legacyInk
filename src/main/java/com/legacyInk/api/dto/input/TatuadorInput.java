package com.legacyInk.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class TatuadorInput {
    @NotBlank
    private String nome;
    @NotNull
    private Integer tempoExperiencia;
    @NotNull
    private BigDecimal avaliacao;

}