package com.legacyInk.domain.model;

import com.legacyInk.domain.model.enums.Cor;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tatuagem tatuagem = (Tatuagem) o;
        return getId() != null && Objects.equals(getId(), tatuagem.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
