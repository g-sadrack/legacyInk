package com.legacyInk.api.dtoconverter;

import com.legacyInk.api.dto.ClienteDTO;
import com.legacyInk.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public ClienteDTO paraDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public List<ClienteDTO> paraDTOLista (List<Cliente> clienteList){
        return clienteList.stream().map(this::paraDTO).collect(Collectors.toList());
    }

}
