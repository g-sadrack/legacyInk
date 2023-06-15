package com.legacyInk.domain.service;

import com.legacyInk.api.dto.ClienteDTO;
import com.legacyInk.domain.model.Cliente;
import com.legacyInk.domain.model.enums.Sexo;
import com.legacyInk.domain.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClienteServiceTest {

    public static final long ID = 1L;
    public static final String NOME = "gabriel";
    public static final int IDADE = 24;
    public static final Sexo SEXO = Sexo.MASCULINO;
    public static final String EMAIL = "gabriel@gmail.com";
    public static final String TELEFONE = "996812321";
    public static final LocalDate ANIVERSARIO = LocalDate.of(1998, 11, 20);

    private Cliente cliente;
    private ClienteDTO clienteDTO;
    private Optional<Cliente> optionalCliente;

    @InjectMocks
    private ClienteService clienteService;
    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startClient();
    }

    @Test
    void quandoBuscarPorIdEntaoRetorneUmCliente() {
        when(clienteRepository.findById(anyLong())).thenReturn(optionalCliente);

        Cliente response = clienteService.validaClienteOuErro(ID);

        assertNotNull(response);// -> se o objeto é nulo, o teste falha

        assertEquals(Cliente.class, response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(IDADE, response.getIdade());
        assertEquals(SEXO, response.getSexo());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(TELEFONE, response.getTelefone());
        assertEquals(ANIVERSARIO, response.getDataNascimento());
    }

    @Test
    void listar() {
    }

    @Test
    void cadastrar() {
    }

    @Test
    void deleta() {
    }

    private void startClient() {
        cliente = new Cliente(ID, NOME, IDADE, SEXO, EMAIL, TELEFONE, ANIVERSARIO);
        clienteDTO = new ClienteDTO(ID, NOME, IDADE, SEXO, EMAIL, TELEFONE, ANIVERSARIO);
        optionalCliente = Optional.of(new Cliente(ID, NOME, IDADE, SEXO, EMAIL, TELEFONE, ANIVERSARIO));
    }
}