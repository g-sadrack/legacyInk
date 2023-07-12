package br.com.legacyink.domain.service;

import br.com.legacyink.api.domainconverter.AgendamentoConvertido;
import br.com.legacyink.api.dto.input.AgendamentoInput;
import br.com.legacyink.domain.exception.AgendamentoNaoEncontradoException;
import br.com.legacyink.domain.model.Agendamento;
import br.com.legacyink.domain.model.Cliente;
import br.com.legacyink.domain.model.Tatuador;
import br.com.legacyink.domain.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;
    private final TatuadorService tatuadorService;
    private final AgendamentoConvertido convertido;
    private final TatuagemService tatuagemService;
    private final ClienteService clienteService;

    @Autowired
    public AgendamentoService(AgendamentoRepository agendamentoRepository, TatuadorService tatuadorService, AgendamentoConvertido convertido, TatuagemService tatuagemService, ClienteService clienteService) {
        this.agendamentoRepository = agendamentoRepository;
        this.tatuadorService = tatuadorService;
        this.convertido = convertido;
        this.tatuagemService = tatuagemService;
        this.clienteService = clienteService;
    }

    public Agendamento validaAgendamentoOuErro(Long agendamentoId) {
        return agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException(agendamentoId));
    }


    public List<Agendamento> listar(Long estudioId, Long tatuadorId) {
        Tatuador tatuador = tatuadorService.buscaTatuadorNoEstudio(estudioId, tatuadorId);
        return tatuador.getAgendamento();
    }

    @Transactional
    public Agendamento agendar(Long estudioId, Long tatuadorId, AgendamentoInput agendamentoInput) {
        Tatuador tatuador = tatuadorService.buscaTatuadorNoEstudio(estudioId, tatuadorId);
        Cliente cliente = clienteService.validaClienteOuErro(agendamentoInput.getCliente().getId());

        Agendamento agendamento = convertido.paraModelo(agendamentoInput);
        agendamento.setCliente(cliente);

        tatuagemService.salvar(agendamento.getTatuagem());
        tatuador.marcarAgendamento(agendamento);

        return agendamentoRepository.save(agendamento);
    }

    @Transactional
    public Agendamento alterarAgendamento(Long estudioId, Long tatuadorId, Long agendamentoId, AgendamentoInput agendamentoInput) {
        Tatuador tatuador = tatuadorService.buscaTatuadorNoEstudio(estudioId, tatuadorId);
        Cliente cliente = clienteService.validaClienteOuErro(agendamentoInput.getCliente().getId());

        Optional<Agendamento> agendamentoExistente = tatuador.getAgendamento()
                .stream()
                .filter(a -> a.getId().equals(agendamentoId))
                .findFirst();

        if (agendamentoExistente.isPresent()) {
            Agendamento agendamento = agendamentoExistente.get();
            convertido.copiaDTOparaModeloDominio(agendamentoInput, agendamento);
            agendamento.setCliente(cliente);
            return agendamento;
        } else {
            throw new AgendamentoNaoEncontradoException("Não existe agendamento");
        }
    }

    @Transactional
    public void desagendar(Long estudioId, Long tatuadorId, Long agendamentoId) {
        Tatuador tatuador = tatuadorService.buscaTatuadorNoEstudio(estudioId, tatuadorId);

        Agendamento agendamento = tatuador.getAgendamento()
                .stream()
                .filter(a -> a.getId().equals(agendamentoId))
                .findFirst()
                .orElseThrow(() -> new AgendamentoNaoEncontradoException(agendamentoId));

        tatuador.desmarcarAgendamento(agendamento);
    }
}