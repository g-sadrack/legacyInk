package br.com.legacyink.api.controller;

import br.com.legacyink.api.domainconverter.ClienteConvertido;
import br.com.legacyink.api.dto.ClienteDTO;
import br.com.legacyink.api.dtoconverter.ClienteDTOConverter;
import br.com.legacyink.domain.model.Cliente;
import br.com.legacyink.domain.repository.ClienteRepository;
import br.com.legacyink.api.dto.input.ClienteInput;
import br.com.legacyink.domain.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteDTOConverter converter;

    @Autowired
    private ClienteConvertido convertido;

    @GetMapping
    public List<ClienteDTO> listar() {
        return converter.paraDTOLista(clienteRepository.findAll());
    }

    @GetMapping("/{clienteId}")
    public ClienteDTO listar(@PathVariable Long clienteId) {
        Cliente cliente = clienteService.validaClienteOuErro(clienteId);
        return converter.paraDTO(cliente);
    }

    @PostMapping
    public ClienteDTO cadastrar(@Validated @RequestBody ClienteInput clienteInput) {
        Cliente cliente = convertido.paraModelo(clienteInput);
        clienteService.cadastrar(cliente);
        return converter.paraDTO(cliente);
    }

    @PutMapping("/{clienteId}")
    public ClienteDTO alterar(@PathVariable Long clienteId, @Validated @RequestBody ClienteInput clienteInput) {
        Cliente cliente = clienteService.validaClienteOuErro(clienteId);
        convertido.copiaDTOparaModeloDominio(clienteInput, cliente);
        Cliente clienteAtualizado = clienteService.cadastrar(cliente);
        return converter.paraDTO(clienteAtualizado);
    }

    @DeleteMapping("/{clienteId}")
    public void deleta(@PathVariable Long clienteId) {
        clienteService.deletar(clienteId);
    }
}
