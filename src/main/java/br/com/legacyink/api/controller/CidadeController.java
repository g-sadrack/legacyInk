package br.com.legacyink.api.controller;

import br.com.legacyink.api.dto.CidadeDTO;
import br.com.legacyink.api.dto.input.CidadeInput;
import br.com.legacyink.api.dtoconverter.CidadeDTOConverter;
import br.com.legacyink.domain.model.Cidade;
import br.com.legacyink.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeService cidadeService;
    private final CidadeDTOConverter converter;

    @Autowired
    public CidadeController(CidadeService cidadeService, CidadeDTOConverter converter) {
        this.cidadeService = cidadeService;
        this.converter = converter;
    }

    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cidadeService.validaCidadeOuErro(cidadeId);
        return converter.paraDTO(cidade);
    }

    @GetMapping()
    public List<CidadeDTO> lista() {
        List<Cidade> cidades = cidadeService.listar();
        return converter.paraDTOLista(cidades);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO cadastrar(@Validated @RequestBody CidadeInput cidadeInput) {
        Cidade cidade = cidadeService.cadastrar(cidadeInput);
        return converter.paraDTO(cidade);
    }

    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar(@PathVariable Long cidadeId, @Validated @RequestBody CidadeInput cidadeInput) {
        Cidade cidadeAtualizada = cidadeService.alterar(cidadeId, cidadeInput);
        return converter.paraDTO(cidadeAtualizada);
    }

    @DeleteMapping("/{cidadeId}")
    public void deletaCidade(@PathVariable Long cidadeId) {
        cidadeService.deleta(cidadeId);
    }

}