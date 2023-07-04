package br.com.legacyink.api.controller;

import br.com.legacyink.api.dto.CidadeDTO;
import br.com.legacyink.api.dto.EstadoDTO;
import br.com.legacyink.api.dto.input.CidadeInput;
import br.com.legacyink.api.dto.input.EstadoIdInput;
import br.com.legacyink.api.dtoconverter.CidadeDTOConverter;
import br.com.legacyink.domain.exception.CidadeNaoEncontradaException;
import br.com.legacyink.domain.model.Cidade;
import br.com.legacyink.domain.model.Estado;
import br.com.legacyink.domain.service.CidadeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CidadeControllerTest {
    public static final long CIDADE_ID = 1L;
    public static final long ID = 1L;
    public static final String NOME_CIDADE = "Goiania";
    public static final int INDEX = 0;
    public static final String MSG_CIDADE_NAO_CONSTA_NO_SISTEMA = String.format("A cidade de ID %d, não consta no sistema", CIDADE_ID);
    public static final String NOME_ESTADO = "Goias";

    private Cidade cidade;
    private Estado estado;
    private CidadeDTO cidadeDTO;
    private CidadeInput cidadeInput;

    @Mock
    private CidadeController controller;
    @Mock
    private CidadeService cidadeService;
    @Mock
    private CidadeDTOConverter converter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new CidadeController(cidadeService, converter);
        startCidade();
    }

    @Test
    void quandoBuscaCidadeEntaoRetornaCidadeDTO() {
        when(cidadeService.validaCidadeOuErro(CIDADE_ID)).thenReturn(cidade);
        when(converter.paraDTO(cidade)).thenReturn(cidadeDTO);

        CidadeDTO response = controller.buscar(CIDADE_ID);

        assertNotNull(response);
        assertEquals(CidadeDTO.class, response.getClass());
        assertEquals(CIDADE_ID, response.getId());
        assertEquals(NOME_CIDADE, response.getNome());
        assertEquals(estado.getId(), response.getEstado().getId());
        assertEquals(estado.getNome(), response.getEstado().getNome());

        verify(cidadeService, times(1)).validaCidadeOuErro(CIDADE_ID);
        verify(converter, times(1)).paraDTO(cidade);
    }

    @Test
    void quandoBuscaCidadeInexistenteEntaoRetornaErro() {
        when(cidadeService.validaCidadeOuErro(anyLong())).thenThrow(new CidadeNaoEncontradaException(MSG_CIDADE_NAO_CONSTA_NO_SISTEMA));

        CidadeNaoEncontradaException exception = assertThrows(CidadeNaoEncontradaException.class, () -> controller.buscar(123L));

        assertEquals(MSG_CIDADE_NAO_CONSTA_NO_SISTEMA, exception.getMessage());
    }

    @Test
    void quandoLstarCidadesEntaoRetornaListaDeCidadesDTO() {
        when(cidadeService.listar()).thenReturn(List.of(cidade));
        when(converter.paraDTOLista(List.of(cidade))).thenReturn(List.of(cidadeDTO));

        List<CidadeDTO> response = controller.lista();

        assertNotNull(response);
        assertEquals(CidadeDTO.class, response.get(INDEX).getClass());
        assertEquals(CIDADE_ID, response.get(INDEX).getId());
        assertEquals(NOME_CIDADE, response.get(INDEX).getNome());
        assertEquals(estado.getId(), response.get(INDEX).getEstado().getId());
        assertEquals(estado.getNome(), response.get(INDEX).getEstado().getNome());

        verify(cidadeService, times(1)).listar();
        verify(converter, times(1)).paraDTOLista(List.of(cidade));

    }

    @Test
    void quandoCadastrarUmaCidadeEntaoRetornaCidadeDTO() {
        when(cidadeService.cadastrar(cidadeInput)).thenReturn(cidade);
        when(converter.paraDTO(cidade)).thenReturn(cidadeDTO);

        CidadeDTO response = controller.cadastrar(cidadeInput);

        assertNotNull(response);
        assertEquals(CidadeDTO.class, response.getClass());
        assertEquals(CIDADE_ID, response.getId());
        assertEquals(NOME_CIDADE, response.getNome());
        assertEquals(estado.getId(), response.getEstado().getId());
        assertEquals(estado.getNome(), response.getEstado().getNome());

        verify(cidadeService, times(1)).cadastrar(cidadeInput);
        verify(converter, times(1)).paraDTO(cidade);
    }

    @Test
    void quandoAtualizarCidadeEntaoRetornaCidadeDTO() {
        when(cidadeService.alterar(anyLong(), any())).thenReturn(cidade);
        when(converter.paraDTO(cidade)).thenReturn(cidadeDTO);

        CidadeDTO response = controller.atualizar(CIDADE_ID, cidadeInput);

        assertNotNull(response);
        assertEquals(CidadeDTO.class, response.getClass());
        assertEquals(CIDADE_ID, response.getId());
        assertEquals(NOME_CIDADE, response.getNome());
        assertEquals(estado.getId(), response.getEstado().getId());
        assertEquals(estado.getNome(), response.getEstado().getNome());

        verify(cidadeService, times(1)).alterar(CIDADE_ID, cidadeInput);
        verify(converter, times(1)).paraDTO(cidade);

    }

    @Test
    void deletaCidade() {
        controller.deletaCidade(CIDADE_ID);

        verify(cidadeService, times(1)).deleta(CIDADE_ID);
    }

    public void startCidade() {
        estado = new Estado(ID, NOME_ESTADO);
        cidade = new Cidade(CIDADE_ID, NOME_CIDADE, estado);

        EstadoDTO estadoDTO = new EstadoDTO(ID, NOME_ESTADO);
        cidadeDTO = new CidadeDTO(CIDADE_ID, NOME_CIDADE, estadoDTO);

        EstadoIdInput estadoIdInput = new EstadoIdInput(1L);
        cidadeInput = new CidadeInput("João Pessoa", estadoIdInput);
    }
}