package br.com.legacyink.api.dto;

import br.com.legacyink.domain.model.enums.Cor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TatuagemDTO {

    private Long id;
    private String descricao;
    private Integer tamanho;
    private Cor cor;
    private String localizacaoNoCorpo;
    private String imagem;
    private BigDecimal preco;

}
