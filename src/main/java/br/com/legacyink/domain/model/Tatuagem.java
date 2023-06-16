package br.com.legacyink.domain.model;

import br.com.legacyink.domain.model.enums.Cor;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Tatuagem {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Integer tamanho;
    @Enumerated(EnumType.STRING)
    private Cor cor;
    private String localizacaoNoCorpo;
    @Column(name = "imagem_url")
    private String imagem;
    private BigDecimal preco;


}
