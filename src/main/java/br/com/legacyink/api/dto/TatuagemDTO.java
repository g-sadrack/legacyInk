package br.com.legacyink.api.dto;

import br.com.legacyink.domain.model.enums.Cor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TatuagemDTO {

    private Long id;
    private String descricao;
    private Integer tamanho;
    private Cor cor;
    private String localizacaoNoCorpo;
    private String imagem;
    private BigDecimal preco;

}
