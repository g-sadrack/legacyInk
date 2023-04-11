package com.legacyInk.api.dto;

import com.legacyInk.domain.model.Endereco;
import com.legacyInk.domain.model.enums.Sexo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClienteDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private Endereco endereco;
    private Integer idade;
    private Sexo sexo;
}
