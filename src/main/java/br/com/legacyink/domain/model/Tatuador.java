package br.com.legacyink.domain.model;

import br.com.legacyink.domain.model.enums.Especialidade;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Integer tempoExperiencia;
    private BigDecimal avaliacao;
    private Boolean ativo = Boolean.TRUE;

    @Enumerated(EnumType.STRING)
    @Column(name = "especialidade")
    @ElementCollection(targetClass = Especialidade.class)
    @CollectionTable(name = "tatuador_especialidades", joinColumns = @JoinColumn(name = "tatuador_id"))
    private List<Especialidade> especialidades = new ArrayList<>();

    @OneToMany
    @JoinTable(name = "agendamento_tatuadores",
            joinColumns = @JoinColumn(name = "tatuador_id"),
            inverseJoinColumns = @JoinColumn(name = "agendamento_id"))
    private List<Agendamento> agendamento = new ArrayList<>();

    public Tatuador(Long id, String nome, Integer tempoExperiencia, BigDecimal avaliacao, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.tempoExperiencia = tempoExperiencia;
        this.avaliacao = avaliacao;
        this.ativo = ativo;
    }

    public void marcarAgendamento(Agendamento agendamento) {
        this.agendamento.add(agendamento);
    }

    public void desmarcarAgendamento(Agendamento agendamento) {
        this.agendamento.remove(agendamento);
    }

    public void ativar() {
        this.ativo = Boolean.TRUE;
    }

    public void inativar() {
        this.ativo = Boolean.FALSE;
    }

}