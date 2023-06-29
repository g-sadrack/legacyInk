package br.com.legacyink.api.controller;

import br.com.legacyink.api.domainconverter.EstadoConvertido;
import br.com.legacyink.api.dto.EstadoDTO;
import br.com.legacyink.api.dto.input.EstadoInput;
import br.com.legacyink.api.dtoconverter.EstadoDTOConverter;
import br.com.legacyink.domain.exception.EstadoNaoEncontradoException;
import br.com.legacyink.domain.model.Estado;
import br.com.legacyink.domain.repository.EstadoRepository;
import br.com.legacyink.domain.service.EstadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EstadoControllerTest {
    public static final long ID = 1L;
    public static final String NOME = "Goias";
    public static final String ESTADO_NAO_CONSTA_NO_SISTEMA = String.format("O estado ID %d , não consta no sistema", ID);
    public static final int INDEX = 0;

    @InjectMocks
    private EstadoController controller;
    @Mock
    private EstadoRepository estadoRepository;
    @Mock
    private EstadoService estadoService;
    @Mock
    private EstadoDTOConverter converter;
    @Mock
    private EstadoConvertido convertido;

    private Estado estado;
    private EstadoDTO estadoDTO;
    private EstadoInput estadoInput;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new EstadoController(estadoService, converter, convertido);
        startEstado();
    }

    @Test
    void quandoBuscarUmEstadoEntaoRetornar() {
        when(estadoService.validaEstadoOuErro(ID)).thenReturn(estado);
        when(converter.paraDTO(estado)).thenReturn(estadoDTO);

        EstadoDTO response = controller.buscar(ID);

        assertNotNull(response);

        assertEquals(EstadoDTO.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(NOME, response.getNome());
        assertEquals(NOME, response.getNome());

        //Verificar se o método estadoService.validaEstadoOuErro(ID) foi chamado uma vez com o ID correto
        verify(estadoService, times(1)).validaEstadoOuErro(ID);

        //Verificar se o método converter.paraDTO(estado) foi chamado uma vez com o objeto estado correto
        verify(converter, times(1)).paraDTO(estado);

        //Verificar se o método estadoRepository não foi chamado:
        verify(estadoRepository, never()).save(any(Estado.class));

        //Verificar se o método estadoService não foi chamado com um ID diferente do esperado:
        verify(estadoService, never()).validaEstadoOuErro(123L);
    }

    @Test
    void quandoBuscarUmEstadoInexistenteEntaoRetornarErro() {
        when(estadoService.validaEstadoOuErro(2L))
                .thenThrow(new EstadoNaoEncontradoException(ESTADO_NAO_CONSTA_NO_SISTEMA));

        EstadoNaoEncontradoException exception = assertThrows(EstadoNaoEncontradoException.class, () -> controller.buscar(2L));

        assertEquals(ESTADO_NAO_CONSTA_NO_SISTEMA, exception.getMessage());
    }


    @Test
    void quandoListarTodosOsEstadosEntaoRetornarUmaListaDTO() {
        when(estadoService.listar()).thenReturn(List.of(estado));
        when(converter.paraDTOList(estadoService.listar())).thenReturn(List.of(estadoDTO));

        List<EstadoDTO> response = controller.listar();

        assertNotNull(response);

        assertEquals(EstadoDTO.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NOME, response.get(INDEX).getNome());

        verify(estadoService, times(2)).listar();
        verify(converter, times(1)).paraDTOList(estadoService.listar());

    }

    @Test
    void quandoCadastrarUmEstadoEntaoRetornarUmEstadoDTO() {
        when(convertido.paraModelo(estadoInput)).thenReturn(estado);
        when(estadoService.salvar(estado)).thenReturn(estado);
        when(converter.paraDTO(estado)).thenReturn(estadoDTO);

        EstadoDTO response = controller.cadastrar(estadoInput);

        assertNotNull(response);

        assertEquals(EstadoDTO.class, estadoDTO.getClass());
        assertEquals(ID, estadoDTO.getId());
        assertEquals(NOME, estadoDTO.getNome());

        verify(estadoService, times(1)).salvar(estado);
        verify(converter, times(1)).paraDTO(estado);

    }


    @Test
    void quandoAlterarUmEstadoEntaoRetornarUmEstadoDTO() {
        // Mock do comportamento do estadoService
        when(estadoService.validaEstadoOuErro(ID)).thenReturn(estado);

        // Mock do comportamento do estadoConvertido
        doNothing().when(convertido).copiaDTOparaModeloDominio(estadoInput, estado);

        // Mock do comportamento do estadoService.salvar()
        when(estadoService.salvar(estado)).thenReturn(estado);

        // Mock do comportamento do estadoDTOConverter
        EstadoDTO estadoDTO = new EstadoDTO();
        when(converter.paraDTO(estado)).thenReturn(estadoDTO);

        // Execução do método alterar
        EstadoDTO resultado = controller.alterar(ID, estadoInput);

        // Verificações
        verify(estadoService).validaEstadoOuErro(ID);
        verify(convertido).copiaDTOparaModeloDominio(estadoInput, estado);
        verify(estadoService).salvar(estado);
        verify(converter).paraDTO(estado);
        assertEquals(estadoDTO, resultado);
    }

    @Test
    void remover() {
        controller.remover(ID);

        verify(estadoService, times(1)).removeEstado(ID);

    }

    public void startEstado() {
        estado = new Estado(ID, NOME);
        estadoDTO = new EstadoDTO(ID, NOME);
        estadoInput = new EstadoInput("são Paulo");
    }
}