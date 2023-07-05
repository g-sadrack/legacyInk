package br.com.legacyink.api.dto.input;

import br.com.legacyink.domain.model.enums.Sexo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ClienteInput {
    @NotBlank
    private String nome;
    @NotNull
    private Integer idade;
    @NotNull
    private Sexo sexo;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String telefone;
    @NotNull
    private LocalDate dataNascimento;
    @Valid
    private EnderecoInput endereco;
}
