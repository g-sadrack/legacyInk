package br.com.legacyink.api.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TatuadorInput {
    @NotBlank
    private String nome;
    @NotNull
    private Integer tempoExperiencia;
    @NotNull
    private BigDecimal avaliacao;

}