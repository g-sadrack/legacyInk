package br.com.legacyink.domain.service;

import br.com.legacyink.domain.exception.ClienteNaoEncontradoException;
import br.com.legacyink.domain.model.Cliente;
import br.com.legacyink.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClienteService {

    public static final String MSG_CLIENTE_INEXISTENTE = "O cliente de ID %d , não consta no sistema";
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CidadeService cidadeService;


    public Cliente validaClienteOuErro(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException(
                        String.format(MSG_CLIENTE_INEXISTENTE, clienteId)));
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente cadastrar(Cliente cliente) {
        Long cidadeId = cliente.getEndereco().getCidade().getId();
        cidadeService.validaCidadeOuErro(cidadeId);
        return clienteRepository.save(cliente);
    }

    public void deletar(Long clienteId) {
        try {
            clienteRepository.deleteById(clienteId);
        } catch (EmptyResultDataAccessException e) {
            throw new ClienteNaoEncontradoException(String.format(MSG_CLIENTE_INEXISTENTE, clienteId));
        }
    }
}
