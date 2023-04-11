package com.legacyInk.api.domainconverter;

import com.legacyInk.api.dto.input.CidadeInput;
import com.legacyInk.domain.model.Cidade;
import com.legacyInk.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeConvertido {

    @Autowired
    ModelMapper modelMapper;

    public Cidade paraModelo(CidadeInput cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }

    public void copiaDTOparaModeloDominio(CidadeInput cidadeInput, Cidade cidade) {
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeInput, cidade);
    }

}
