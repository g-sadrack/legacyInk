package com.legacyInk.api.controller;

import com.legacyInk.api.domainconverter.TatuadorConvertido;
import com.legacyInk.api.dto.TatuadorDTO;
import com.legacyInk.api.dto.input.TatuadorInput;
import com.legacyInk.api.dtoconverter.TatuadorDTOConverter;
import com.legacyInk.domain.model.Tatuador;
import com.legacyInk.domain.service.TatuadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tatuadores")
public class TatuadorController {

    @Autowired
    private TatuadorService tatuadorService;

    @Autowired
    private TatuadorDTOConverter converter;

    @Autowired
    private TatuadorConvertido convertido;

    @GetMapping("/{tatuadorId}")
    public TatuadorDTO buscar(@PathVariable Long tatuadorId) {
        Tatuador tatuador = tatuadorService.validarOuErro(tatuadorId);
        return converter.paraDTO(tatuador);
    }

    @GetMapping
    public List<TatuadorDTO> buscar() {
        List<Tatuador> tatuadores = tatuadorService.listarTatuadores();
        return converter.paraDTOLista(tatuadores);
    }

    @PostMapping
    public TatuadorDTO cadastrar(@Validated @RequestBody TatuadorInput tatuadorInput) {
        Tatuador tatuador = convertido.paraModelo(tatuadorInput);
        return converter.paraDTO(tatuadorService.cadastra(tatuador));
    }

    @PutMapping("/{tatuadorId}")
    public TatuadorDTO cadastrar(@PathVariable Long tatuadorId, @Validated @RequestBody TatuadorInput tatuadorInput) {
        Tatuador tatuador = tatuadorService.validarOuErro(tatuadorId);
        convertido.copiaDTOparaModeloDominio(tatuadorInput, tatuador);
        return converter.paraDTO(tatuadorService.cadastra(tatuador));
    }

    @DeleteMapping("/{tatuadorId}")
    public void remover(@PathVariable Long tatuadorId) {
        tatuadorService.deletar(tatuadorId);
    }

}
