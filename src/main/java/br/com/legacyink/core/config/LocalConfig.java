package br.com.legacyink.core.config;

import br.com.legacyink.domain.model.Estado;
import br.com.legacyink.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local") //perfil de teste
public class LocalConfig {

    @Autowired
    private EstadoRepository estadoRepository;

    @Bean
    public void startDB(){
        Estado estado1 = new Estado(null, "Goias");
        Estado estado2 = new Estado(null, "Minas Gerais");

        estadoRepository.saveAll(List.of(estado1,estado2));
    }
}