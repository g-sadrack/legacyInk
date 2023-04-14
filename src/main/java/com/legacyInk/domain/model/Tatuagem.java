package com.legacyInk.domain.model;

import com.legacyInk.domain.model.enums.Cor;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private Integer tamanho;
    @NotBlank
    private Cor cor;
    @NotBlank
    private String localizacaoNoCorpo;
    @NotBlank
    @Column(name = "imagem_url")
    private String imagem;
    @NotBlank
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
