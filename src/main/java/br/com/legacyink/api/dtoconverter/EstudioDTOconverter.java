package br.com.legacyink.api.dtoconverter;

import br.com.legacyink.api.dto.EstudioDTO;
import br.com.legacyink.domain.model.Estudio;
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
