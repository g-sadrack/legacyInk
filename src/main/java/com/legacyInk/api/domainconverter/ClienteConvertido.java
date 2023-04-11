package com.legacyInk.api.domainconverter;

import com.legacyInk.api.dto.ClienteDTO;
import com.legacyInk.api.dto.input.ClienteInput;
import com.legacyInk.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteConvertido {

    @Autowired
    private ModelMapper modelMapper;

    public Cliente paraModelo(ClienteInput clienteDTO) {
        return modelMapper.map(clienteDTO, Cliente.class);
    }

    public void copiaDTOparaModeloDominio (ClienteInput clienteInput, Cliente cliente){
        modelMapper.map(clienteInput, cliente);
    }

}
