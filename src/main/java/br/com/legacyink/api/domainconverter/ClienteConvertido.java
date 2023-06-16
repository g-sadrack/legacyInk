package br.com.legacyink.api.domainconverter;

import br.com.legacyink.domain.model.Cliente;
import br.com.legacyink.api.dto.input.ClienteInput;
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
