package br.com.legacyink.api.dto.input;

import br.com.legacyink.api.dto.ClienteDTO;
import br.com.legacyink.api.dto.TatuagemDTO;
import br.com.legacyink.domain.model.enums.StatusAgendamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AgendamentoInput {

    @NotNull
    @Valid
    private ClienteDTO cliente;
    @NotNull
    @Valid
    private TatuagemDTO tatuagem;
    @NotNull
    private StatusAgendamento status;
    @NotNull
    private LocalDateTime dataHora;

}
