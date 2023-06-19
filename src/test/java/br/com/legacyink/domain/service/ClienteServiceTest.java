package br.com.legacyink.domain.service;

import br.com.legacyink.domain.exception.ClienteNaoEncontradoException;
import br.com.legacyink.domain.model.Cidade;
import br.com.legacyink.domain.model.Cliente;
import br.com.legacyink.domain.model.Endereco;
import br.com.legacyink.domain.model.Estado;
import br.com.legacyink.domain.model.enums.Sexo;
import br.com.legacyink.domain.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClienteServiceTest {

    public static final long ID = 1L;
    public static final String NOME = "gabriel";
    public static final int IDADE = 24;
    public static final Sexo SEXO = Sexo.MASCULINO;
    public static final String EMAIL = "gabriel@gmail.com";
    public static final String TELEFONE = "996812321";
    public static final LocalDate ANIVERSARIO = LocalDate.of(1998, 11, 20);
    public static final String MSG_NAO_CONSTA_NO_SISTEMA = "O cliente de ID %d , não consta no sistema";
    public static final int INDEX = 0;
    public static final long ESTADOID = 1L;
    public static final long CIDADEID = 1L;

    private Cidade cidade;
    private Estado estado;
    private Endereco endereco;
    private Cliente cliente;
    private Optional<Cliente> optionalCliente;

    @Mock
    private CidadeService cidadeService;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startClient();
    }

    @Test
    void quandoBuscarPorIdEntaoRetorneUmCliente() {
        when(clienteRepository.findById(anyLong())).thenReturn(optionalCliente);

        Cliente cliente = clienteService.validaClienteOuErro(ID);

        assertNotNull(cliente);// -> se o objeto é nulo, o teste falha

        assertEquals(Cliente.class, cliente.getClass());

        assertEquals(ID, cliente.getId());
        assertEquals(NOME, cliente.getNome());
        assertEquals(IDADE, cliente.getIdade());
        assertEquals(SEXO, cliente.getSexo());
        assertEquals(EMAIL, cliente.getEmail());
        assertEquals(TELEFONE, cliente.getTelefone());
        assertEquals(ANIVERSARIO, cliente.getDataNascimento());
        assertEquals(endereco, cliente.getEndereco());
    }

    @Test
    void quandoBuscarPorIdENaoEncontrarEntaoRetorneUmErro() {
        when(clienteRepository.findById(anyLong())).thenThrow(new ClienteNaoEncontradoException(MSG_NAO_CONSTA_NO_SISTEMA));

        try {
            clienteService.validaClienteOuErro(ID);
        } catch (Exception exception) {
            assertEquals(ClienteNaoEncontradoException.class, exception.getClass());
            assertEquals(MSG_NAO_CONSTA_NO_SISTEMA, exception.getMessage());
        }

    }

    @Test
    void quandoEncontrarTodosEntaoRetorneUmaListadeClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> clientes = clienteService.listar();

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        assertEquals(Cliente.class, clientes.get(INDEX).getClass());

        assertEquals(ID, clientes.get(INDEX).getId());
        assertEquals(NOME, clientes.get(INDEX).getNome());
        assertEquals(IDADE, clientes.get(INDEX).getIdade());
        assertEquals(SEXO, clientes.get(INDEX).getSexo());
        assertEquals(EMAIL, clientes.get(INDEX).getEmail());
        assertEquals(TELEFONE, clientes.get(INDEX).getTelefone());
        assertEquals(ANIVERSARIO, clientes.get(INDEX).getDataNascimento());
        assertEquals(endereco, clientes.get(INDEX).getEndereco());
    }

    @Test
    void quandoCadastrarEntaoRetorneSucesso() {
        when(cidadeService.validaCidadeOuErro(CIDADEID)).thenReturn(cidade);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente response = clienteService.cadastrar(cliente);

        verify(cidadeService).validaCidadeOuErro(CIDADEID);
        verify(clienteRepository).save(cliente);

        assertEquals(cliente, response);
    }

    @Test
    void quandoRemoveEstadoSucesso() {
        clienteService.deletar(ID);

        verify(clienteRepository).deleteById(ID);
    }


    @Test
    void deleta() {
        doThrow(EmptyResultDataAccessException.class)
                .when(clienteRepository)
                .deleteById(anyLong());
        assertThrows(ClienteNaoEncontradoException.class, () ->
                clienteService.deletar(ID)
        );
    }

    private void startClient() {
        estado = new Estado(ESTADOID, "Rio de Janeiro");
        cidade = new Cidade(CIDADEID, "Pao de acucar", estado);
        endereco = new Endereco("72863230", "Rua das Flores", "03", "aaa", "asas", cidade);

        cliente = new Cliente(ID, NOME, IDADE, SEXO, EMAIL, TELEFONE, ANIVERSARIO, endereco);
        optionalCliente = Optional.of(new Cliente(ID, NOME, IDADE, SEXO, EMAIL, TELEFONE, ANIVERSARIO, endereco));
    }
}