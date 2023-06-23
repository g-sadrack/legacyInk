package br.com.legacyink.api.dtoconverter;

import br.com.legacyink.api.dto.TatuadorDTO;
import br.com.legacyink.domain.model.Tatuador;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TatuadorDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public TatuadorDTO paraDTO(Tatuador tatuador) {
        return modelMapper.map(tatuador, TatuadorDTO.class);
    }

    public List<TatuadorDTO> paraDTOLista(List<Tatuador> clienteList) {
        return clienteList.stream().map(this::paraDTO).collect(Collectors.toList());
    }

}