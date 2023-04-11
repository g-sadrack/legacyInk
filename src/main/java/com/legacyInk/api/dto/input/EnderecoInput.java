package com.legacyInk.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EnderecoInput {
    @NotBlank
    private String cep;

    @NotBlank
    private String rua;

    @NotBlank
    private String numero;

    @NotBlank
    private String complemento;

    @NotBlank
    private String logradouro;

    @Valid
    private CidadeIdInput cidade;

}