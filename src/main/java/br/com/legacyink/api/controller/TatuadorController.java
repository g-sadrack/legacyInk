package br.com.legacyink.api.controller;

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

    private final TatuadorService tatuadorService;
    private final TatuadorDTOConverter converter;

    @Autowired
    public TatuadorController(TatuadorService tatuadorService, TatuadorDTOConverter converter) {
        this.tatuadorService = tatuadorService;
        this.converter = converter;
    }

    @GetMapping("/{tatuadorId}")
    public TatuadorDTO buscarTatuador(@PathVariable Long estudioId, @PathVariable Long tatuadorId) {
        Tatuador tatuador = tatuadorService.buscaTatuadorNoEstudio(estudioId, tatuadorId);
        return converter.paraDTO(tatuador);
    }

    @GetMapping
    public List<TatuadorDTO> listar(@PathVariable Long estudioId) {
        List<Tatuador> tatuadores = tatuadorService.listarTodosTatuadoresDoEstudio(estudioId);
        return converter.paraDTOLista(tatuadores);
    }

    @PostMapping
    public TatuadorDTO cadastrarTatuadorEmEstudio(@PathVariable Long estudioId, @Validated @RequestBody TatuadorInput tatuadorInput) {
        Tatuador tatuador = tatuadorService.cadastra(estudioId, tatuadorInput);
        return converter.paraDTO(tatuador);
    }

    @PutMapping("/{tatuadorId}")
    public TatuadorDTO atualizarTatuador(@PathVariable Long estudioId,
                                         @PathVariable Long tatuadorId,
                                         @Validated @RequestBody TatuadorInput tatuadorInput) {
        Tatuador tatuador = tatuadorService.atualiza(estudioId, tatuadorId, tatuadorInput);
        return converter.paraDTO(tatuador);
    }
}
