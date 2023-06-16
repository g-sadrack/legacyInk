package br.com.legacyink.api.controller;

import br.com.legacyink.api.domainconverter.CidadeConvertido;
import br.com.legacyink.api.dtoconverter.CidadeDTOConverter;
import br.com.legacyink.domain.exception.EstadoNaoEncontradoException;
import br.com.legacyink.domain.exception.NegocioException;
import br.com.legacyink.domain.model.Cidade;
import br.com.legacyink.domain.service.CidadeService;
import br.com.legacyink.api.dto.CidadeDTO;
import br.com.legacyink.api.dto.input.CidadeInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeConvertido convertido;

    @Autowired
    private CidadeDTOConverter converter;

    @GetMapping("/{cidadeId}")
    public CidadeDTO busca(@PathVariable Long cidadeId) {
        Cidade cidade = cidadeService.validaEnderecoOuErro(cidadeId);
        return converter.paraDTO(cidade);
    }

    @GetMapping()
    public List<CidadeDTO> lista() {
        return converter.paraDTOLista(cidadeService.listar());
    }

    @PostMapping
    public CidadeDTO cadastrar(@Validated @RequestBody CidadeInput cidadeInput) {
        try {
            Cidade cidade = convertido.paraModelo(cidadeInput);
            return converter.paraDTO(cidadeService.cadastrar(cidade));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar(@PathVariable Long cidadeId, @Validated @RequestBody CidadeInput cidadeInput) {
        Cidade cidade = cidadeService.validaEnderecoOuErro(cidadeId);
        convertido.copiaDTOparaModeloDominio(cidadeInput, cidade);
        Cidade cidadeAtualizada = cidadeService.cadastrar(cidade);
        return converter.paraDTO(cidadeAtualizada);
    }

    @DeleteMapping("/{cidadeId}")
    public void deletaCidade(@PathVariable Long cidadeId) {
        cidadeService.deleta(cidadeId);
    }

}
