package br.com.legacyink.domain.service;

import br.com.legacyink.domain.exception.EntidadeEmUsoException;
import br.com.legacyink.domain.exception.EstudioNaoEncontradoException;
import br.com.legacyink.domain.model.Estudio;
import br.com.legacyink.domain.repository.EstudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.List;

@Service
public class EstudioService {

    private final EstudioRepository estudioRepository;

    @Autowired
    public EstudioService(EstudioRepository estudioRepository) {
        this.estudioRepository = estudioRepository;
    }

    public Estudio buscaEstudioOuErro(Long id) {
        return estudioRepository.findById(id).orElseThrow(
                () -> new EstudioNaoEncontradoException(String.format("Estudio não encontrado: %d", id)));
    }

    public List<Estudio> listarEstudios() {
        return estudioRepository.findAll();
    }

    @Transient
    public Estudio adicionar(Estudio estudio) {
        return estudioRepository.save(estudio);
    }

    @Transient
    public void remover(Long id) {
        Estudio estudio = estudioRepository.findById(id)
                .orElseThrow(() -> new EstudioNaoEncontradoException(id));

        if (!estudio.getEstoque().isEmpty()) {
            throw new EntidadeEmUsoException("O Estudio possui itens no estoque");
        }

        if (!estudio.getTatuadores().isEmpty()) {
            throw new EntidadeEmUsoException("O Estudio possui tatuadores associados");
        }

        if (!estudio.getClientes().isEmpty()) {
            throw new EntidadeEmUsoException("O Estudio possui clientes associados");
        }
        estudioRepository.deleteById(id);
    }
}