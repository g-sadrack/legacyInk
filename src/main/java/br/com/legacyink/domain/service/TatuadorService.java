package br.com.legacyink.domain.service;

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


    public Tatuador validaTatuadorOuErro(Long tatuadorId) {
        return tatuadorRepository.findById(tatuadorId)
                .orElseThrow(() -> new TatuadorNaoEncontradoException(
                        String.format(MSG_TATUADOR_NAO_CONSTA_NO_SISTEMA, tatuadorId)));
    }


    public Tatuador buscaTatuador(Long estudioId, Long tatuadorId) {
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);

        for (Tatuador tatuador : estudio.getTatuadores()) {
            if (tatuador.getId().equals(tatuadorId)) {
                return tatuador;
            }
        }

        return null;
    }

    public List<Tatuador> listarTatuadores(Long estudioId) {
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);

        if (estudio.getId() != null) {
            return estudio.getTatuadores();
        }

        return Collections.emptyList();
    }

    @Transactional
    public Tatuador cadastra(Tatuador tatuador) {
        return tatuadorRepository.save(tatuador);
    }

    /*
    TODO
    @Transactional
    public void deletar(Long tatuadorId) {
        try {
            tatuadorRepository.deleteById(tatuadorId);
        } catch (EmptyResultDataAccessException e) {
            throw new TatuadorNaoEncontradoException(String.format(MSG_TATUADOR_NAO_CONSTA_NO_SISTEMA, tatuadorId));
        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("O Tatuador de ID %d está vinculado a algum estudio",tatuadorId));
        }

    }*/
}
