package br.com.legacyink.domain.service;

import br.com.legacyink.api.domainconverter.EstudioConvertido;
import br.com.legacyink.api.dto.input.EstudioInput;
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
    private final EstudioConvertido convertido;

    @Autowired
    public EstudioService(EstudioRepository estudioRepository, EstudioConvertido convertido) {
        this.estudioRepository = estudioRepository;
        this.convertido = convertido;
    }

    public Estudio buscaEstudioOuErro(Long id) {
        return estudioRepository.findById(id).orElseThrow(
                () -> new EstudioNaoEncontradoException(id));
    }

    public List<Estudio> listarEstudios() {
        return estudioRepository.findAll();
    }

    @Transient
    public Estudio cadastrarEstudio(EstudioInput estudioInput) {
        Estudio estudio = convertido.paraModelo(estudioInput);
        return estudioRepository.save(estudio);
    }

    @Transient
    public Estudio alterarEstudio(Long estudioId, EstudioInput estudioInput) {
        Estudio estudio = buscaEstudioOuErro(estudioId);
        convertido.copiaDTOparaModeloDominio(estudioInput, estudio);
        return estudioRepository.save(estudio);
    }

    @Transient
    public void removerEstudio(Long id) {
        Estudio estudio = estudioRepository.findById(id)
                .orElseThrow(() -> new EstudioNaoEncontradoException(id));

        if (!estudio.getEstoque().isEmpty()) {
            throw new EntidadeEmUsoException("O Estudio possui itens em estoque associado");
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