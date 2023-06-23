package br.com.legacyink.api.domainconverter;

import br.com.legacyink.domain.model.Estado;
import br.com.legacyink.api.dto.input.EstadoInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoConvertido {

    @Autowired
    private ModelMapper modelMapper;

    public Estado paraModelo(EstadoInput estadoInput) {
        return modelMapper.map(estadoInput, Estado.class);
    }

    public void copiaDTOparaModeloDominio(EstadoInput estadoInput, Estado estado) {
        modelMapper.map(estadoInput, estado);
    }

}
