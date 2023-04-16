package com.legacyInk.api.controller;

import com.legacyInk.api.domainconverter.ClienteConvertido;
import com.legacyInk.api.dto.ClienteDTO;
import com.legacyInk.api.dto.input.ClienteInput;
import com.legacyInk.api.dtoconverter.ClienteDTOConverter;
import com.legacyInk.domain.model.Cliente;
import com.legacyInk.domain.repository.ClienteRepository;
import com.legacyInk.domain.service.ClienteService;
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
        Cliente cliente = clienteService.validaUsuarioOuErro(clienteId);
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
        Cliente cliente = clienteService.validaUsuarioOuErro(clienteId);
        convertido.copiaDTOparaModeloDominio(clienteInput, cliente);
        Cliente clienteAtualizado = clienteService.cadastrar(cliente);
        return converter.paraDTO(clienteAtualizado);
    }

    @DeleteMapping("/{clienteId}")
    public void deleta(@PathVariable Long clienteId) {
        clienteService.deletar(clienteId);
    }

}
