package br.com.legacyink.api.domainconverter;

import br.com.legacyink.domain.model.Agendamento;
import br.com.legacyink.api.dto.input.AgendamentoInput;
import br.com.legacyink.domain.model.Cliente;
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
        agendamento.setCliente(new Cliente());
        modelMapper.map(agendamentoInput, agendamento);
    }
}
