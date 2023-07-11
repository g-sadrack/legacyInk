package br.com.legacyink.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

}
