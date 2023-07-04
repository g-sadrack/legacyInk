package br.com.legacyink.api.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @Valid
    private EnderecoInput endereco;

}