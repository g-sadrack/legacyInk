package br.com.legacyink.api.dto.input;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
public class CidadeInput {

    @NotBlank
    private String nome;
    @Valid
    @NotNull
    private EstadoIdInput estado;

}