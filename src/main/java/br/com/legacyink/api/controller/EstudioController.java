package br.com.legacyink.api.controller;

import br.com.legacyink.api.dto.EstudioDTO;
import br.com.legacyink.api.dto.input.EstudioInput;
import br.com.legacyink.api.dtoconverter.EstudioDTOconverter;
import br.com.legacyink.domain.model.Estudio;
import br.com.legacyink.domain.service.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudios")
public class EstudioController {

    private final EstudioService estudioService;
    private final EstudioDTOconverter converter;

    @Autowired
    public EstudioController(EstudioService estudioService, EstudioDTOconverter converter) {
        this.estudioService = estudioService;
        this.converter = converter;
    }

    @GetMapping("/{estudioId}")
    public EstudioDTO buscarEstudio(@PathVariable Long estudioId) {
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);
        return converter.paraDTO(estudio);
    }

    @GetMapping
    public List<EstudioDTO> listarEstudios() {
        List<Estudio> estudios = estudioService.listarEstudios();
        return converter.paraDTOLista(estudios);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstudioDTO cadastrarEstudio(@Validated @RequestBody EstudioInput estudioInput) {
        Estudio estudio = estudioService.cadastrarEstudio(estudioInput);
        return converter.paraDTO(estudio);
    }

    @PutMapping("/{estudioId}")
    public EstudioDTO alterarEstudio(@PathVariable Long estudioId, @Validated @RequestBody EstudioInput estudioInput) {
        Estudio estudio = estudioService.alterarEstudio(estudioId, estudioInput);
        return converter.paraDTO(estudio);
    }

    @DeleteMapping("/{estudioId}")
    public void removerEstudio(@PathVariable Long estudioId) {
        estudioService.removerEstudio(estudioId);
    }
}