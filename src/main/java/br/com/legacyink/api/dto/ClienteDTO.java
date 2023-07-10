package br.com.legacyink.api.dto;

import br.com.legacyink.api.dto.resumo.EnderecoResumoDTO;
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
    private EnderecoResumoDTO endereco;

}