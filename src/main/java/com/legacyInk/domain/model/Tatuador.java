package com.legacyInk.domain.model;

import com.legacyInk.domain.model.enums.Especialidade;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Tatuador {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ElementCollection(targetClass = Especialidade.class)
    private List<Especialidade> especialidades;
    private Integer tempoExperiencia;
    private BigDecimal avaliacao;

}