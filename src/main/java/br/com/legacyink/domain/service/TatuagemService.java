package br.com.legacyink.domain.service;

import br.com.legacyink.domain.model.Tatuagem;
import br.com.legacyink.domain.repository.TatuagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TatuagemService {
    TatuagemRepository tatuagemRepository;

    @Autowired
    public TatuagemService(TatuagemRepository tatuagemRepository){
        this.tatuagemRepository = tatuagemRepository;
    }
    @Transactional
    public void salvar(Tatuagem tatuagem){
        tatuagemRepository.save(tatuagem);
    }

}
