package com.legacyInk.domain.model;

import com.legacyInk.domain.model.enums.StatusAgendamento;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Agendamento {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "tatuagem_id")
    private Tatuagem tatuagem;
   @ManyToOne
   @JoinColumn(name = "tatuador_id")
   private Tatuador tatuador;
    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;
    private LocalDateTime dataHora;
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataAtualizacao;

}