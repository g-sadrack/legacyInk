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
    @OneToMany
    @JoinTable(name = "agendamento_tatuadores",
            joinColumns = @JoinColumn(name = "tatuador_id"),
            inverseJoinColumns = @JoinColumn(name = "agendamento_id"))
    private List<Agendamento> agendamento = new ArrayList<>();

    public Tatuador(Long id, String nome, List<Especialidade> especialidades, Integer tempoExperiencia, BigDecimal avaliacao) {
        this.id = id;
        this.nome = nome;
        this.especialidades = especialidades;
        this.tempoExperiencia = tempoExperiencia;
        this.avaliacao = avaliacao;
    }

    public void marcarAgendamento(Agendamento agendamento) {
        this.agendamento.add(agendamento);
    }

    public void desmarcarAgendamento(Agendamento agendamento) {
        this.agendamento.remove(agendamento);
    }

}