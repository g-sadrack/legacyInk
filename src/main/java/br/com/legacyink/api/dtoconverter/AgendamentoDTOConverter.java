package br.com.legacyink.api.dtoconverter;

import br.com.legacyink.api.dto.AgendamentoDTO;
import br.com.legacyink.domain.model.Agendamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AgendamentoDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    public AgendamentoDTO paraDTO(Agendamento agendamento) {
        return modelMapper.map(agendamento, AgendamentoDTO.class);
    }

    public List<AgendamentoDTO> paraDTOLista(List<Agendamento> agendamentoList) {
        return agendamentoList.stream().map(this::paraDTO).collect(Collectors.toList());
    }

}