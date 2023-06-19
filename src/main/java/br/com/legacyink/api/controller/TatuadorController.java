package br.com.legacyink.api.controller;

import br.com.legacyink.api.domainconverter.TatuadorConvertido;
import br.com.legacyink.api.dto.TatuadorDTO;
import br.com.legacyink.api.dto.input.TatuadorInput;
import br.com.legacyink.api.dtoconverter.TatuadorDTOConverter;
import br.com.legacyink.domain.model.Tatuador;
import br.com.legacyink.domain.service.TatuadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudio/{estudioId}/tatuadores")
public class TatuadorController {

    @Autowired
    private TatuadorService tatuadorService;

    @Autowired
    private TatuadorDTOConverter converter;

    @Autowired
    private TatuadorConvertido convertido;

    @GetMapping("/{tatuadorId}")
    public TatuadorDTO buscar(@PathVariable Long estudioId, @PathVariable Long tatuadorId) {
        Tatuador tatuador = tatuadorService.buscaTatuador(estudioId, tatuadorId);
        return converter.paraDTO(tatuador);
    }

    @GetMapping
    public List<TatuadorDTO> listar(@PathVariable Long estudioId) {
        List<Tatuador> tatuadores = tatuadorService.listarTatuadores(estudioId);
        return converter.paraDTOLista(tatuadores);
    }

    @PostMapping
    public TatuadorDTO cadastrar(@Validated @RequestBody TatuadorInput tatuadorInput) {
        Tatuador tatuador = convertido.paraModelo(tatuadorInput);
        return converter.paraDTO(tatuadorService.cadastra(tatuador));
    }

    @PutMapping("/{tatuadorId}")
    public TatuadorDTO cadastrar(@PathVariable Long tatuadorId, @Validated @RequestBody TatuadorInput tatuadorInput) {
        Tatuador tatuador = tatuadorService.validaTatuadorOuErro(tatuadorId);
        convertido.copiaDTOparaModeloDominio(tatuadorInput, tatuador);
        return converter.paraDTO(tatuadorService.cadastra(tatuador));
    }

}
