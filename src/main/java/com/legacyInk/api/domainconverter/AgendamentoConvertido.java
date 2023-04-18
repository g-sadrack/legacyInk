package com.legacyInk.api.domainconverter;

import com.legacyInk.api.dto.input.AgendamentoInput;
import com.legacyInk.domain.model.Agendamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AgendamentoConvertido {

    @Autowired
    private ModelMapper modelMapper;

    public Agendamento paraModelo(AgendamentoInput agendamentoInput) {
        return modelMapper.map(agendamentoInput, Agendamento.class);
    }

    public void copiaDTOparaModeloDominio(AgendamentoInput agendamentoInput,
                                          Agendamento agendamento) {
        modelMapper.map(agendamentoInput, agendamento);
    }
}
