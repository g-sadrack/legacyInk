package br.com.legacyink.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;

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

    public Cidade(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cidade(Long id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }
}
