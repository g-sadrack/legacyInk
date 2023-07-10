package br.com.legacyink.api.dto.input;

import br.com.legacyink.domain.model.enums.StatusAgendamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoInput {

    @Valid
    private ClienteIdInput cliente;
    @NotNull
    @Valid
    private TatuagemInput tatuagem;
    private StatusAgendamento status;
    private LocalDateTime dataHora;

}
