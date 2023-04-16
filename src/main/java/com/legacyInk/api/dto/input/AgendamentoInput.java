package com.legacyInk.api.dto.input;

import com.legacyInk.api.dto.ClienteDTO;
import com.legacyInk.api.dto.TatuagemDTO;
import com.legacyInk.domain.model.enums.StatusAgendamento;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
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
