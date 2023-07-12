package br.com.legacyink.domain.model;

import br.com.legacyink.domain.exception.NegocioException;
import br.com.legacyink.domain.model.enums.StatusAgendamento;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @Enumerated(EnumType.STRING)
    private StatusAgendamento status = StatusAgendamento.AGENDADO;
    @Column(name = "sessao")
    private OffsetDateTime dataHora;
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataAtualizacao;

    public void confirmar() {
        setStatus(StatusAgendamento.CONFIRMADO);
        setDataHora(OffsetDateTime.now());
    }
    public void cancelar() {
        setStatus(StatusAgendamento.CANCELADO);
        setDataHora(OffsetDateTime.now());
    }

    public void naoCompareceu() {
        setStatus(StatusAgendamento.NAO_COMPARECEU);
        setDataHora(OffsetDateTime.now());
    }

    private void setStatus(StatusAgendamento novoStatus) {
        if (getStatus().naoPodeAlterarPara(novoStatus)) {
            throw new NegocioException(
                    String.format("O status do pedido %d não pode ser alterado de %s para %s",
                            getId(), getStatus().getDescricao(), novoStatus.getDescricao()));
        }
       this.status = novoStatus;
    }
}