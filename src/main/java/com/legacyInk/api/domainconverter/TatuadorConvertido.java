package com.legacyInk.api.domainconverter;

import com.legacyInk.api.dto.input.TatuadorInput;
import com.legacyInk.domain.model.Tatuador;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TatuadorConvertido {

    @Autowired
    private ModelMapper modelMapper;

    public Tatuador paraModelo(TatuadorInput tatuadorInput) {
        return modelMapper.map(tatuadorInput, Tatuador.class);
    }

    public void copiaDTOparaModeloDominio(TatuadorInput tatuadorInput, Tatuador tatuador) {
        modelMapper.map(tatuadorInput, tatuador);
    }

}