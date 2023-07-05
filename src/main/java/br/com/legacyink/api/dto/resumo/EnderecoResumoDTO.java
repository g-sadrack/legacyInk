package br.com.legacyink.api.dto.resumo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResumoDTO {
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String logradouro;
    private CidadeResumoDTO cidade;
}