package br.com.legacyink.domain.service;

import br.com.legacyink.domain.exception.EstadoNaoEncontradoException;
import br.com.legacyink.domain.model.Estado;
import br.com.legacyink.domain.repository.EstadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class EstadoServiceTest {

    public static final long ID = 1L;
    public static final String NOME = "Goias";
    public static final String ESTADO_NAO_CONSTA_NO_SISTEMA = String.format("O estado ID %d , não consta no sistema", ID);
    public static final int INDEX = 0;
    @Mock
    private EstadoRepository estadoRepository;
    private EstadoService estadoService;

    private Estado estado;
    private Optional<Estado> optionalEstado;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        estadoService = new EstadoService(estadoRepository);
        startEstado();
    }

    @Test
    void quandoValidarEstadoRetorneUmEstado() {
        when(estadoRepository.findById(any())).thenReturn(optionalEstado);

        Estado response = estadoService.validaEstadoOuErro(ID);

        assertNotNull(response);
        assertEquals(Estado.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
    }

    @Test
    void quandoNaoValidarEstadoEntaoRetorneUmErro() {
        when(estadoRepository.findById(anyLong())).thenReturn(Optional.empty());

        EstadoNaoEncontradoException exception = assertThrows(
                EstadoNaoEncontradoException.class, () -> estadoService.validaEstadoOuErro(ID));

        assertEquals(ESTADO_NAO_CONSTA_NO_SISTEMA, exception.getMessage());
    }


    @Test
    void quandoListarTodosEntaoRetorneUmaLista() {
        when(estadoRepository.findAll()).thenReturn(List.of(estado));

        List<Estado> estados = estadoService.listar();

        assertNotNull(estados);
        assertEquals(Estado.class, estados.get(INDEX).getClass());
        assertEquals(ID, estados.get(INDEX).getId());
        assertEquals(NOME, estados.get(INDEX).getNome());
    }

    @Test
    void quandoSalvarUmEstadoEntaoRetorneEle() {
        when(estadoRepository.save(any())).thenReturn(estado);

        Estado estado = estadoService.salvar(this.estado);

        assertNotNull(estado);
        assertEquals(Estado.class, estado.getClass());
        assertEquals(ID, estado.getId());
        assertEquals(NOME, estado.getNome());
    }

    @Test
    void quandoRemoveEstadoSucesso() {

        estadoService.removeEstado(ID);
        //-> Usando o Mockito VERIFICAMOS se deleteById
        verify(estadoRepository).deleteById(ID);
        verify(estadoRepository).flush();
    }

    @Test
    void quandoRemoveEstadoInexistenteErro() {
        /*
            Usando o Mockito, configuramos o mock estadoRepository para lançar uma exceção
            EmptyResultDataAccessException quando o método deleteById
            é chamado com o ID de estado fornecido. Isso simula o comportamento de quando o
            método deleteById não encontra o estado correspondente.
         */
        doThrow(EmptyResultDataAccessException.class)
                .when(estadoRepository)
                .deleteById(ID);

        assertThrows(EstadoNaoEncontradoException.class, () -> estadoService.removeEstado(ID));
    }

    public void startEstado() {
        estado = new Estado(ID, NOME);
        optionalEstado = Optional.of(new Estado(ID, NOME));
    }
}