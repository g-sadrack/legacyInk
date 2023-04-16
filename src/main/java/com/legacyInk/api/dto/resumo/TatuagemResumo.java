package com.legacyInk.api.dto.resumo;

import com.legacyInk.domain.model.enums.Cor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class TatuagemResumo {

    private String descricao;
    private Integer tamanho;
    private BigDecimal preco;
    private Cor cor;
    private String localizacaoNoCorpo;
    private String imagem;

}
