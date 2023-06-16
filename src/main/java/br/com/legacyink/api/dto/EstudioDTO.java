package br.com.legacyink.api.dto;

import br.com.legacyink.domain.model.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EstudioDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cnpj;
    private String razaoSocial;
    private String redesSociais;
    private LocalDate horario;
    private Endereco endereco;

}
