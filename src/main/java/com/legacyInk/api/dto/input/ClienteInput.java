package com.legacyInk.api.dto.input;

import com.legacyInk.domain.model.enums.Sexo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private LocalDate dataNascimento;
    @Valid
    private EnderecoInput endereco;
    @NotNull
    private Integer idade;
    @NotNull
    private Sexo sexo;
}
