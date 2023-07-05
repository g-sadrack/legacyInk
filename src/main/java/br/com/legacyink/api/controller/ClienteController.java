package br.com.legacyink.api.controller;

import br.com.legacyink.api.dto.ClienteDTO;
import br.com.legacyink.api.dto.input.ClienteInput;
import br.com.legacyink.api.dtoconverter.ClienteDTOConverter;
import br.com.legacyink.domain.model.Cliente;
import br.com.legacyink.domain.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private final ClienteService clienteService;
    private final ClienteDTOConverter converter;

    @Autowired
    public ClienteController(ClienteService clienteService, ClienteDTOConverter converter) {
        this.clienteService = clienteService;
        this.converter = converter;
    }

    @GetMapping
    public List<ClienteDTO> listarClientes() {
        List<Cliente> clientes = clienteService.listar();
        return converter.paraDTOLista(clientes);
    }

    @GetMapping("/{clienteId}")
    public ClienteDTO buscarCliente(@PathVariable Long clienteId) {
        Cliente cliente = clienteService.validaClienteOuErro(clienteId);
        return converter.paraDTO(cliente);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO cadastrarCliente(@Validated @RequestBody ClienteInput clienteInput) {
        Cliente cliente = clienteService.cadastrarCliente(clienteInput);
        return converter.paraDTO(cliente);
    }

    @PutMapping("/{clienteId}")
    public ClienteDTO alterarCliente(@PathVariable Long clienteId, @Validated @RequestBody ClienteInput clienteInput) {
        Cliente clienteAtualizado = clienteService.atualizar(clienteId, clienteInput);
        return converter.paraDTO(clienteAtualizado);
    }

    @DeleteMapping("/{clienteId}")
    public void deleta(@PathVariable Long clienteId) {
        clienteService.deletar(clienteId);
    }

}