package com.legacyInk.api.domainconverter;

import com.legacyInk.api.dto.input.EstudioInput;
import com.legacyInk.domain.model.Cidade;
import com.legacyInk.domain.model.Estudio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EstudioConvertido {

    @Autowired
    private ModelMapper modelMapper;

    public Estudio paraModelo(EstudioInput estudioInput) {
        return modelMapper.map(estudioInput, Estudio.class);
    }

    public void copiaDTOparaModeloDominio(EstudioInput estudioInput, Estudio estudio) {
        estudio.getEndereco().setCidade(new Cidade());
        modelMapper.map(estudioInput, estudio);
    }
}
