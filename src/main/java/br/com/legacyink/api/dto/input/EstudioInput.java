package br.com.legacyink.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class EstudioInput {
    @NotBlank
    private String nome;
    @NotBlank
    private String telefone;
    @NotBlank
    private String email;
    @NotBlank
    private String cnpj;
    @NotBlank
    private String razaoSocial;
    @NotBlank
    private String redesSociais;
    @NotNull
    private LocalDate horario;
    @Valid
    private EnderecoInput endereco;

}