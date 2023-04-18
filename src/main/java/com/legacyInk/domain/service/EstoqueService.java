package com.legacyInk.domain.service;

import com.legacyInk.domain.exception.EntidadeNaoEncontradaException;
import com.legacyInk.domain.model.Agendamento;
import com.legacyInk.domain.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
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
        agendamentoRepository.deleteById(agendamentoId);
    }


}
