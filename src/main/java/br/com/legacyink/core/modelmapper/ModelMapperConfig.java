package br.com.legacyink.core.modelmapper;

import br.com.legacyink.api.dto.resumo.EnderecoResumoDTO;
import br.com.legacyink.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        //Usando o método para passar o nome da classe Endereco para o EnderecoResumoDTO
        TypeMap<Endereco, EnderecoResumoDTO> enderecoToEnderecoResumoDTO =
                modelMapper.createTypeMap(Endereco.class, EnderecoResumoDTO.class);

        enderecoToEnderecoResumoDTO.<String>addMapping(source -> source.getCidade().getEstado().getNome(),
                (destino, valor) -> destino.getCidade().setEstado(valor));


        return modelMapper;
    }
}
