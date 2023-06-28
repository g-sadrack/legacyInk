package br.com.legacyink.domain.service;

import br.com.legacyink.api.domainconverter.AgendamentoConvertido;
import br.com.legacyink.api.dto.input.AgendamentoInput;
import br.com.legacyink.domain.exception.AgendamentoNaoEncontradoException;
import br.com.legacyink.domain.model.Agendamento;
import br.com.legacyink.domain.model.Tatuador;
import br.com.legacyink.domain.model.enums.StatusAgendamento;
import br.com.legacyink.domain.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;
    private final TatuadorService tatuadorService;
    private final AgendamentoConvertido convertido;

    @Autowired
    public AgendamentoService(AgendamentoRepository agendamentoRepository, TatuadorService tatuadorService, AgendamentoConvertido convertido) {
        this.agendamentoRepository = agendamentoRepository;
        this.tatuadorService = tatuadorService;
        this.convertido = convertido;
    }

    public Agendamento validaEnderecoOuErro(Long agendamentoId) {
        return agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException(agendamentoId));
    }


    public List<Agendamento> listar(Long estudioId, Long tatuadorId) {
        Tatuador tatuador = tatuadorService.buscaTatuadorNoEstudio(estudioId, tatuadorId);
        return tatuador.getAgendamento();
    }

    @Transactional
    public Agendamento alterar(Long estudioId, Long tatuadorId, AgendamentoInput agendamentoInput) {
        Agendamento agendamentoNovo = convertido.paraModelo(agendamentoInput);
        Tatuador tatuador = tatuadorService.buscaTatuadorNoEstudio(estudioId, tatuadorId);

        for (Agendamento agendamento : tatuador.getAgendamento()) {
            if (agendamento.getId().equals(agendamentoNovo.getId())) {
                convertido.copiaDTOparaModeloDominio(agendamentoInput, agendamento);
            }
            tatuador.marcarAgendamento(agendamento);
        }
        return agendamentoNovo;
    }

    @Transactional
    public Agendamento agendar(Long estudioId, Long tatuadorId, AgendamentoInput agendamentoInput) {
        Tatuador tatuador = tatuadorService.buscaTatuadorNoEstudio(estudioId, tatuadorId);
        Agendamento agendamento = convertido.paraModelo(agendamentoInput);
        tatuador.marcarAgendamento(agendamento);
        return agendamentoRepository.save(agendamento);
    }

    @Transactional
    public void desagendar(Long estudioId, Long tatuadorId, Long agendamentoId) {
        Tatuador tatuador = tatuadorService.buscaTatuadorNoEstudio(estudioId, tatuadorId);
        try {
            List<Agendamento> agendamentos = tatuador.getAgendamento();
            for (Agendamento agendamento : agendamentos) {
                if (Objects.equals(agendamento.getId(), agendamentoId)) {
                    agendamento.setStatus(StatusAgendamento.CANCELADO);
                }
            }
            tatuador.desmarcarAgendamento(validaEnderecoOuErro(agendamentoId));
        } catch (EmptyResultDataAccessException e) {
            throw new AgendamentoNaoEncontradoException(agendamentoId);
        }
    }
}