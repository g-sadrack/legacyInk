package br.com.legacyink.domain.service;

import br.com.legacyink.api.domainconverter.TatuadorConvertido;
import br.com.legacyink.api.dto.input.TatuadorInput;
import br.com.legacyink.domain.exception.TatuadorNaoEncontradoException;
import br.com.legacyink.domain.model.Estudio;
import br.com.legacyink.domain.model.Tatuador;
import br.com.legacyink.domain.repository.TatuadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class TatuadorService {

    public static final String MSG_TATUADOR_NAO_CONSTA_NO_SISTEMA = "Tatuador com o ID %d não consta no sistema";
    @Autowired
    private TatuadorRepository tatuadorRepository;

    @Autowired
    private EstudioService estudioService;

    @Autowired
    private TatuadorConvertido convertido;


    public Tatuador validaTatuadorOuErro(Long tatuadorId) {
        return tatuadorRepository.findById(tatuadorId)
                .orElseThrow(() -> new TatuadorNaoEncontradoException(
                        String.format(MSG_TATUADOR_NAO_CONSTA_NO_SISTEMA, tatuadorId)));
    }


    public Tatuador buscaTatuadorNoEstudio(Long estudioId, Long tatuadorId) {
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);

        return estudio.getTatuadores().stream()
                .filter(tatuador -> tatuador.getId().equals(tatuadorId))
                .findFirst()
                .orElseThrow(() -> new TatuadorNaoEncontradoException(
                        String.format("O Tatuador de ID %d não consta no sistema ou não faz parte desse estudio", tatuadorId)));
    }

    public List<Tatuador> listarTatuadores(Long estudioId) {
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);
        if (estudio.getId() != null) {
            return estudio.getTatuadores();
        }
        return Collections.emptyList();
    }

    @Transactional
    public Tatuador cadastra(Long estudioId, Tatuador tatuador) {
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);
        estudio.associarTatuador(tatuador);
        return tatuadorRepository.save(tatuador);
    }

    @Transactional
    public Tatuador atualiza(Long estudioId, Long tatuadorId, TatuadorInput tatuadorInput) {
        Tatuador tatuador = this.buscaTatuadorNoEstudio(estudioId, tatuadorId);
        convertido.copiaDTOparaModeloDominio(tatuadorInput, tatuador);
        return tatuadorRepository.save(tatuador);
    }
}
