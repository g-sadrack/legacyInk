package br.com.legacyink.api.domainconverter;

import br.com.legacyink.domain.model.Tatuador;
import br.com.legacyink.api.dto.input.TatuadorInput;
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