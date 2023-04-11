package com.legacyInk.api.domainconverter;

import com.legacyInk.api.dto.input.EstadoInput;
import com.legacyInk.domain.model.Estado;
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
