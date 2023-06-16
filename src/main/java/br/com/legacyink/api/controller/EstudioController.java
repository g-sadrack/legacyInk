package br.com.legacyink.api.controller;

import br.com.legacyink.api.domainconverter.EstudioConvertido;
import br.com.legacyink.api.dto.EstudioDTO;
import br.com.legacyink.api.dtoconverter.EstudioDTOconverter;
import br.com.legacyink.domain.model.Estudio;
import br.com.legacyink.api.dto.input.EstudioInput;
import br.com.legacyink.domain.service.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudios")
public class EstudioController {

    @Autowired
    private EstudioService estudioService;

    @Autowired
    private EstudioDTOconverter converter;

    @Autowired
    private EstudioConvertido convertido;

    @GetMapping("/{estudioId}")
    public EstudioDTO buscar(@PathVariable Long estudioId) {
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);
        return converter.paraDTO(estudio);
    }

    @GetMapping
    public List<EstudioDTO> listar() {
        List<Estudio> estudios = estudioService.listarEstudios();
        return converter.paraDTOLista(estudios);
    }

    @PostMapping
    public EstudioDTO cadastra(@Validated @RequestBody EstudioInput estudioInput) {
        Estudio estudio = convertido.paraModelo(estudioInput);
        return converter.paraDTO(estudioService.adicionar(estudio));
    }

    @PutMapping("/{estudioId}")
    public EstudioDTO altera(@PathVariable Long estudioId, @Validated @RequestBody EstudioInput estudioInput) {
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);
        convertido.copiaDTOparaModeloDominio(estudioInput, estudio);
        return converter.paraDTO(estudioService.adicionar(estudio));
    }

    @DeleteMapping("/{estudioId}")
    public void remove(@PathVariable Long estudioId) {
        estudioService.remover(estudioId);
    }

}