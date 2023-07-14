package br.com.legacyink.domain.repository.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.OffsetDateTime;

@Getter
@Setter
public class AgendamentoFilter {

    private Long clienteId;
    private String codigo;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime dataCadastro;

}
