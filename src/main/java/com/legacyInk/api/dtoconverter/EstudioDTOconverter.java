package com.legacyInk.api.dtoconverter;

import com.legacyInk.api.dto.EstudioDTO;
import com.legacyInk.domain.model.Estudio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EstudioDTOconverter {

    @Autowired
    private ModelMapper modelMapper;

    public EstudioDTO paraDTO(Estudio estudio) {
        return modelMapper.map(estudio, EstudioDTO.class);
    }

    public List<EstudioDTO> paraDTOLista(List<Estudio> estudios) {
        return estudios.stream().map(this::paraDTO).collect(Collectors.toList());
    }

}
