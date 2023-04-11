package com.legacyInk.domain.service;

import com.legacyInk.domain.exception.ClienteIdNaoEncontradoException;
import com.legacyInk.domain.model.Cliente;
import com.legacyInk.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente validaUsuarioOuErro(Long clienteId) {
        return clienteRepository.findById(clienteId).orElseThrow(
                () -> new ClienteIdNaoEncontradoException(String.format("O cliente de ID %d , não consta no sistema", clienteId)));
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente cadastrar(Cliente cliente) {
        Long id = cliente.getEndereco().getCidade().getId();

        return clienteRepository.save(cliente);
    }

}
