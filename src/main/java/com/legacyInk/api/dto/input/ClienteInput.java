package com.legacyInk.api.dto.input;

import com.legacyInk.domain.model.Endereco;
import com.legacyInk.domain.model.enums.Sexo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Getter
@Setter
public class ClienteInput {
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String telefone;
    @NotBlank
    private LocalDate dataNascimento;
    @Valid
    @NotBlank
    private Endereco endereco;
    @NotBlank
    private Integer idade;
    @NotBlank
    private Sexo sexo;
}
