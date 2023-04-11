package com.legacyInk.api.dtoconverter;

import com.legacyInk.api.dto.CidadeDTO;
import com.legacyInk.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CidadeDTO paraDTO(Cidade cidade) {
        return modelMapper.map(cidade, CidadeDTO.class);
    }

    public List<CidadeDTO> paraDTOLista(List<Cidade> cidadeList) {
        return cidadeList.stream().map(this::paraDTO).collect(Collectors.toList());
    }

}
