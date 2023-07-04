package br.com.legacyink.api.dto.resumo;

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
public class TatuagemResumo {

    private String descricao;
    private Integer tamanho;
    private BigDecimal preco;
    private Cor cor;
    private String localizacaoNoCorpo;
    private String imagem;

}