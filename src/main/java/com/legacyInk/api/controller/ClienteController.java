package com.legacyInk.api.controller;

import com.legacyInk.api.domainconverter.ClienteConvertido;
import com.legacyInk.api.dto.ClienteDTO;
import com.legacyInk.api.dto.input.ClienteInput;
import com.legacyInk.api.dtoconverter.ClienteDTOConverter;
import com.legacyInk.domain.model.Cliente;
import com.legacyInk.domain.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteDTOConverter converter;

    @Autowired
    private ClienteConvertido convertido;

    @GetMapping
    public List<ClienteDTO> listar() {
        List<Cliente> listar = clienteService.listar();
        return converter.paraDTOLista(listar);
    }

    @GetMapping("/{usuarioId}")
    public ClienteDTO listar(@PathVariable Long usuarioId) {
        Cliente cliente = clienteService.validaUsuarioOuErro(usuarioId);
        return converter.paraDTO(cliente);
    }

    @PostMapping
    public ClienteDTO cadastrar (@RequestBody ClienteInput clienteInput) {
        Cliente cliente = convertido.paraModelo(clienteInput);
        cliente = clienteService.validaUsuarioOuErro(cliente.getId());

        return converter.paraDTO(cliente);
    }

}
