package com.legacyInk.api.dto;

import com.legacyInk.api.dto.resumo.ClienteResumo;
import com.legacyInk.api.dto.resumo.TatuadorResumo;
import com.legacyInk.api.dto.resumo.TatuagemResumo;
import com.legacyInk.domain.model.enums.StatusAgendamento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendamentoDTO {

    private Long id;
    private ClienteResumo cliente;
    private TatuagemResumo tatuagem;
    private TatuadorResumo tatuador;
    private StatusAgendamento status;
    private LocalDateTime dataHora;

}