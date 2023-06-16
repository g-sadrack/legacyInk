package br.com.legacyink.domain.service;

import br.com.legacyink.domain.exception.AgendamentoNaoEncontradoException;
import br.com.legacyink.domain.model.Agendamento;
import br.com.legacyink.domain.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public static final String MSG_AGENDAMENTO_NAO_ENCONTRADO = "O Agendamento de ID %d , não consta no sistema";


    public Agendamento validaEnderecoOuErro(Long agendamentoId) {
        return agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException(
                        String.format(MSG_AGENDAMENTO_NAO_ENCONTRADO, agendamentoId)));
    }


    public List<Agendamento> listar() {
        return agendamentoRepository.findAll();
    }

    @Transactional
    public Agendamento cadastrar(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    @Transactional
    public void deleta(Long agendamentoId) {
        try {
            agendamentoRepository.deleteById(agendamentoId);
        } catch (EmptyResultDataAccessException e) {
            throw new AgendamentoNaoEncontradoException(
                    String.format(MSG_AGENDAMENTO_NAO_ENCONTRADO, agendamentoId)
            );
        }
    }


}
