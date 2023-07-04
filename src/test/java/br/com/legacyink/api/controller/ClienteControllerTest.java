package br.com.legacyink.api.controller;

import br.com.legacyink.api.dto.ClienteDTO;
import br.com.legacyink.api.dto.input.ClienteInput;
import br.com.legacyink.api.dto.input.EnderecoInput;
import br.com.legacyink.api.dtoconverter.ClienteDTOConverter;
import br.com.legacyink.domain.model.Cidade;
import br.com.legacyink.domain.model.Cliente;
import br.com.legacyink.domain.model.Endereco;
import br.com.legacyink.domain.model.Estado;
import br.com.legacyink.domain.model.enums.Sexo;
import br.com.legacyink.domain.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    public static final Long ID = 1L;
    public static final String NOME = "gabriel";
    public static final int IDADE = 24;
    public static final Sexo SEXO = Sexo.MASCULINO;
    public static final String EMAIL = "gabriel@gmail.com";
    public static final String TELEFONE = "996812321";
    public static final LocalDate DATA_DE_NASCIMENTO = LocalDate.of(1998, 11, 20);
    public static final int INDEX = 0;
    public static final long ESTADOID = 1L;
    public static final long CIDADEID = 1L;

    private Endereco endereco;
    private ClienteInput clienteInput;
    private Cliente cliente;
    private ClienteDTO clienteDTO;
    @Mock
    private ClienteService clienteService;
    @Mock
    private ClienteDTOConverter converter;
    @Mock
    private ClienteController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new ClienteController(clienteService, converter);
        startClient();
    }

    @Test
    void listar() {
        when(clienteService.listar()).thenReturn(List.of(cliente));
        when(converter.paraDTOLista(anyList())).thenReturn(List.of(clienteDTO));

        List<ClienteDTO> response = controller.listarClientes();
        assertNotNull(response);

        assertEquals(1, response.size());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NOME, response.get(INDEX).getNome());
        assertEquals(IDADE, response.get(INDEX).getIdade());
        assertEquals(SEXO, response.get(INDEX).getSexo());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(TELEFONE, response.get(INDEX).getTelefone());
        assertEquals(DATA_DE_NASCIMENTO, response.get(INDEX).getDataNascimento());
        assertEquals(endereco, response.get(INDEX).getEndereco());

        verify(clienteService, times(1)).listar();
        verify(converter, times(1)).paraDTOLista(anyList());

    }

    @Test
    void quandoBuscarUmclienteEntaoRetornaUmClienteDTO() {
        when(clienteService.validaClienteOuErro(ID)).thenReturn(cliente);
        when(converter.paraDTO(cliente)).thenReturn(clienteDTO);

        ClienteDTO response = controller.buscarCliente(ID);
        assertNotNull(response);

        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(IDADE, response.getIdade());
        assertEquals(SEXO, response.getSexo());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(TELEFONE, response.getTelefone());
        assertEquals(DATA_DE_NASCIMENTO, response.getDataNascimento());
        assertEquals(endereco, response.getEndereco());

        verify(clienteService, times(1)).validaClienteOuErro(ID);
        verify(converter, times(1)).paraDTO(cliente);
    }

    @Test
    void quandoCadastrarUmClienteEntaoRetornaUmClienteDTO() {
        when(clienteService.cadastrarCliente(clienteInput)).thenReturn(cliente);
        when(converter.paraDTO(cliente)).thenReturn(clienteDTO);

        ClienteDTO response = controller.cadastrarCliente(clienteInput);

        assertNotNull(response);

        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(IDADE, response.getIdade());
        assertEquals(SEXO, response.getSexo());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(TELEFONE, response.getTelefone());
        assertEquals(DATA_DE_NASCIMENTO, response.getDataNascimento());
        assertEquals(endereco, response.getEndereco());

        verify(clienteService, times(1)).cadastrarCliente(clienteInput);
        verify(converter, times(1)).paraDTO(cliente);

    }

    @Test
    void quandoAlterarumClienteEntaoRetornaUmClienteDTO() {
        when(clienteService.atualizar(ID, clienteInput)).thenReturn(cliente);
        when(converter.paraDTO(cliente)).thenReturn(clienteDTO);

        ClienteDTO response = controller.alterarCliente(ID, clienteInput);

        assertNotNull(response);

        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(IDADE, response.getIdade());
        assertEquals(SEXO, response.getSexo());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(TELEFONE, response.getTelefone());
        assertEquals(DATA_DE_NASCIMENTO, response.getDataNascimento());
        assertEquals(endereco, response.getEndereco());

        verify(clienteService, times(1)).atualizar(ID, clienteInput);
        verify(converter, times(1)).paraDTO(cliente);

    }

    @Test
    void quandoDeletarUmClienteEntaoRetorneNotFound() {
        controller.deleta(ID);

        verify(clienteService, times(1)).deletar(ID);
    }

    private void startClient() {
        Estado estado = new Estado(ESTADOID, "Rio de Janeiro");
        Cidade cidade = new Cidade(CIDADEID, "Pao de acucar", estado);
        endereco = new Endereco("72863230", "Rua das Flores", "03", "aaa", "asas", cidade);

        cliente = new Cliente(ID, NOME, IDADE, SEXO, EMAIL, TELEFONE, DATA_DE_NASCIMENTO, endereco);
        clienteInput = new ClienteInput(NOME, IDADE, SEXO, EMAIL, TELEFONE, DATA_DE_NASCIMENTO, new EnderecoInput());
        clienteDTO = new ClienteDTO(ID, NOME, IDADE, SEXO, EMAIL, TELEFONE, DATA_DE_NASCIMENTO, endereco);
    }

}