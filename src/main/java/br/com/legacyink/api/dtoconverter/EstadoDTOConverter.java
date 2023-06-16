package br.com.legacyink.api.dtoconverter;

import br.com.legacyink.api.dto.EstadoDTO;
import br.com.legacyink.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    public EstadoDTO paraDTO(Estado estado) {
        return modelMapper.map(estado, EstadoDTO.class);
    }

    public List<EstadoDTO> paraDTOList (List<Estado> estadoList){
        return estadoList.stream().map(this::paraDTO).collect(Collectors.toList());
    }
}
