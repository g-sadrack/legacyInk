package br.com.legacyink.api.dto;

import br.com.legacyink.domain.model.Endereco;
import br.com.legacyink.domain.model.enums.Sexo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private Integer idade;
    private Sexo sexo;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private Endereco endereco;

    public ClienteDTO(Long id, String nome, Integer idade, Sexo sexo, String email, String telefone, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }
}
