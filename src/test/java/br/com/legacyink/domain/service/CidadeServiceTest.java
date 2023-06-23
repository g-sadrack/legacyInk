package br.com.legacyink.domain.service;

import br.com.legacyink.domain.exception.CidadeNaoEncontradaException;
import br.com.legacyink.domain.exception.EstadoNaoEncontradoException;
import br.com.legacyink.domain.model.Cidade;
import br.com.legacyink.domain.model.Estado;
import br.com.legacyink.domain.repository.CidadeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class CidadeServiceTest {

    public static final long CIDADE_ID = 1L;
    public static final long ID = 1L;
    public static final String NOME_CIDADE = "Goiania";
    public static final int INDEX = 0;
    public static final String MSG_CIDADE_NAO_CONSTA_NO_SISTEMA = String.format("A cidade de ID %d, não consta no sistema", CIDADE_ID);
    public static final String NOME_ESTADO = "Goias";
    public static final String MSG_ESTADO_NAO_ENCONTRADO = String.format("O estado ID %d, não consta no sistema", 2L);

    @Mock
    private EstadoService estadoService;
    private CidadeService cidadeService;
    @Mock
    private CidadeRepository cidadeRepository;

    private Cidade cidade;
    private Estado estado;
    private Optional<Cidade> optionalCidade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cidadeService = new CidadeService(cidadeRepository, estadoService);
        startCidade();
    }

    @Test
    void quandoValidaEnderecoEntaoSucesso() {
        when(cidadeRepository.findById(anyLong())).thenReturn(optionalCidade);

        Cidade response = cidadeService.validaCidadeOuErro(CIDADE_ID);

        assertNotNull(response);
        assertEquals(Cidade.class, response.getClass());
        assertEquals(CIDADE_ID, response.getId());
        assertEquals(NOME_CIDADE, response.getNome());

    }

    @Test
    void quandoNaoValidaEnderecoEntaoErro() {
        when(cidadeRepository.findById(anyLong())).thenReturn(Optional.empty());

        CidadeNaoEncontradaException cidadeNaoEncontradaException = assertThrows(CidadeNaoEncontradaException.class,
                () -> cidadeService.validaCidadeOuErro(CIDADE_ID));

        assertEquals(MSG_CIDADE_NAO_CONSTA_NO_SISTEMA, cidadeNaoEncontradaException.getMessage());
    }

    @Test
    void quandoListarCidadeEntaoRetornaCidade() {
        when(cidadeRepository.findAll()).thenReturn(List.of(cidade));
        List<Cidade> response = cidadeService.listar();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Cidade.class, response.get(INDEX).getClass());
        assertEquals(CIDADE_ID, response.get(INDEX).getId());
        assertEquals(NOME_CIDADE, response.get(INDEX).getNome());

    }

    @Test
    void quandoSalvarCidadeEntaoRetornaCidade() {
        when(estadoService.validaEstadoOuErro(anyLong()))
                .thenReturn(estado);
        when(cidadeRepository.save(cidade)).thenReturn(cidade);

        Cidade cadastrar = cidadeService.cadastrar(cidade);

        assertNotNull(cadastrar);
    }

    @Test
    void quandoSalvarCidadeComEstadoInexistenteEntaoRetornaErro() {
        Estado estadoInexistente = new Estado(2L, "Tangamandapio");

        when(estadoService.validaEstadoOuErro(estadoInexistente.getId()))
                .thenThrow(new EstadoNaoEncontradoException(MSG_ESTADO_NAO_ENCONTRADO));
        cidade.setEstado(estadoInexistente);

        EstadoNaoEncontradoException estadoNaoEncontradoException = assertThrows(
                EstadoNaoEncontradoException.class, () -> cidadeService.cadastrar(cidade));

        assertEquals(MSG_ESTADO_NAO_ENCONTRADO, estadoNaoEncontradoException.getMessage());

    }

    @Test
    void quandoDeletarUmaCidadeEntaoSucesso() {
        cidadeService.deleta(CIDADE_ID);

        verify(cidadeRepository).deleteById(anyLong());
    }

    @Test
    void quandoDeletarCidadeNaoEncontradaEntaoErro() {
        doThrow(EmptyResultDataAccessException.class)
                .when(cidadeRepository)
                .deleteById(ID);

        assertThrows(CidadeNaoEncontradaException.class, () -> cidadeService.deleta(CIDADE_ID));
    }

    void startCidade() {
        estado = new Estado(ID, NOME_ESTADO);
        cidade = new Cidade(CIDADE_ID, NOME_CIDADE, estado);
        optionalCidade = Optional.of(cidade);
    }
}