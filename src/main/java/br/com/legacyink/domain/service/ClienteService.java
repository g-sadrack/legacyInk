package br.com.legacyink.domain.service;

import br.com.legacyink.api.domainconverter.ClienteConvertido;
import br.com.legacyink.api.dto.input.ClienteInput;
import br.com.legacyink.domain.exception.ClienteNaoEncontradoException;
import br.com.legacyink.domain.model.Cidade;
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

    private final ClienteRepository clienteRepository;
    private final CidadeService cidadeService;
    private final ClienteConvertido convertido;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, CidadeService cidadeService, ClienteConvertido convertido) {
        this.clienteRepository = clienteRepository;
        this.cidadeService = cidadeService;
        this.convertido = convertido;
    }

    public Cliente validaClienteOuErro(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente cadastrarCliente(ClienteInput clienteInput) {
        Cliente cliente = convertido.paraModelo(clienteInput);
        Long cidadeId = cliente.getEndereco().getCidade().getId();

        Cidade cidade = cidadeService.validaCidadeOuErro(cidadeId);
        cliente.getEndereco().setCidade(cidade);

        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente atualizar(Long clienteId, ClienteInput clienteInput) {
        Cliente cliente = validaClienteOuErro(clienteId);
        convertido.copiaDTOparaModeloDominio(clienteInput, cliente);

        Long cidadeId = cliente.getEndereco().getCidade().getId();
        Cidade cidade = cidadeService.validaCidadeOuErro(cidadeId);

        cliente.getEndereco().setCidade(cidade);

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
