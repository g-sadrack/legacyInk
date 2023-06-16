package br.com.legacyink.api.domainconverter;

import br.com.legacyink.domain.model.Cidade;
import br.com.legacyink.domain.model.Estado;
import br.com.legacyink.api.dto.input.CidadeInput;
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
