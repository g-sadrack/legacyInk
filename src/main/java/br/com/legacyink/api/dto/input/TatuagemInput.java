package br.com.legacyink.api.dto.input;

import br.com.legacyink.domain.model.enums.Cor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TatuagemInput {

    private String descricao;
    @NotNull
    private Integer tamanho;
    @NotNull
    private Cor cor;
    @NotBlank
    private String localizacaoNoCorpo;
    @NotBlank
    private String imagem;
    @NotNull
    private BigDecimal preco;

}
