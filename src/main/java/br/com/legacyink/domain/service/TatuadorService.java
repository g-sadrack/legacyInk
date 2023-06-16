package br.com.legacyink.domain.service;

import br.com.legacyink.domain.exception.TatuadorNaoEncontradoException;
import br.com.legacyink.domain.model.Tatuador;
import br.com.legacyink.domain.repository.TatuadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TatuadorService {

    public static final String MSG_TATUADOR_NAO_CONSTA_NO_SISTEMA = "Tatuador com o ID %d não consta no sistema";
    @Autowired
    private TatuadorRepository tatuadorRepository;


    public Tatuador validarOuErro(Long tatuadorId) {
        return tatuadorRepository.findById(tatuadorId)
                .orElseThrow(() -> new TatuadorNaoEncontradoException(
                        String.format(MSG_TATUADOR_NAO_CONSTA_NO_SISTEMA, tatuadorId)));
    }

    public List<Tatuador> listarTatuadores() {
        return tatuadorRepository.findAll();
    }

    @Transactional
    public Tatuador cadastra(Tatuador tatuador) {
        return tatuadorRepository.save(tatuador);
    }
    @Transactional
    public void deletar(Long tatuadorId) {
        try {
            tatuadorRepository.deleteById(tatuadorId);
        } catch (EmptyResultDataAccessException e) {
            throw new TatuadorNaoEncontradoException(String.format(MSG_TATUADOR_NAO_CONSTA_NO_SISTEMA, tatuadorId));
        }

    }
}
