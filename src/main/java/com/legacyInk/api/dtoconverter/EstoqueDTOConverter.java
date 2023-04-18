package com.legacyInk.api.dtoconverter;

import com.legacyInk.api.dto.EstoqueDTO;
import com.legacyInk.domain.model.Estoque;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstoqueDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public EstoqueDTO paraDTO(Estoque estoque) {
        return modelMapper.map(estoque, EstoqueDTO.class);
    }

    public List<EstoqueDTO> paraDTOLista(List<Estoque> clientes) {
        return clientes.stream().map(this::paraDTO).collect(Collectors.toList());
    }

}
