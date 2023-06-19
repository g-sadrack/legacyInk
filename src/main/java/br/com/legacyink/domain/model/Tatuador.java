package br.com.legacyink.domain.model;

import br.com.legacyink.domain.model.enums.Especialidade;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tatuador {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "especialidade")
    @ElementCollection(targetClass = Especialidade.class)
    @CollectionTable(name = "tatuador_especialidades", joinColumns = @JoinColumn(name = "tatuador_id"))
    private List<Especialidade> especialidades = new ArrayList<>();
    private Integer tempoExperiencia;
    private BigDecimal avaliacao;

}