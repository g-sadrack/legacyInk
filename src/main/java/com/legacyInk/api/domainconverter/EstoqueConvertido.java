package com.legacyInk.api.domainconverter;

import com.legacyInk.api.dto.input.EstoqueInput;
import com.legacyInk.domain.model.Estoque;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstoqueConvertido {

    @Autowired
    private ModelMapper modelMapper;

    public Estoque paraModelo(EstoqueInput estoqueInput) {
        return modelMapper.map(estoqueInput, Estoque.class);
    }

    public void copiaDTOparaModeloDominio(Estoque estoqueInput, Estoque estoque) {
        modelMapper.map(estoqueInput, estoque);
    }

}
