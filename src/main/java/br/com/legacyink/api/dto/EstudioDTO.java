package br.com.legacyink.api.dto;

import br.com.legacyink.domain.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstudioDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cnpj;
    private Boolean aberto;
    private String razaoSocial;
    private String redesSociais;
    private Endereco endereco;

}