package br.com.legacyink.api.dto;

import br.com.legacyink.api.dto.resumo.ClienteResumo;
import br.com.legacyink.api.dto.resumo.TatuadorResumo;
import br.com.legacyink.api.dto.resumo.TatuagemResumo;
import br.com.legacyink.domain.model.enums.StatusAgendamento;
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