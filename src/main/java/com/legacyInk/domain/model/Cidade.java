package com.legacyInk.domain.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Cidade {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Valid
    @ManyToOne
    @JoinColumn(nullable = false)
    private Estado estado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cidade cidade = (Cidade) o;
        return getId() != null && Objects.equals(getId(), cidade.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
