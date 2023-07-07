package br.com.legacyink.api.controller;

import br.com.legacyink.api.domainconverter.TatuadorConvertido;
import br.com.legacyink.api.dto.TatuadorDTO;
import br.com.legacyink.api.dto.input.TatuadorInput;
import br.com.legacyink.api.dtoconverter.TatuadorDTOConverter;
import br.com.legacyink.domain.model.*;
import br.com.legacyink.domain.model.enums.Especialidade;
import br.com.legacyink.domain.service.TatuadorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class TatuadorControllerTest {

    public static final long TATUADOR_ID = 1L;
    public static final String NOME = "João";
    public static final Integer TEMPO_EXPERIENCIA = 5;
    private Tatuador tatuador;
    private TatuadorInput tatuadorInput;
    private TatuadorDTO tatuadorDTO;
    private Estudio estudio;
    private BigDecimal avaliacao;
    private final List<Especialidade> especialidades = new ArrayList<>();

    @Mock
    private TatuadorConvertido convertido;
    @Mock
    private TatuadorController controller;
    @Mock
    private TatuadorService tatuadorService;
    @Mock
    private TatuadorDTOConverter converter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new TatuadorController(tatuadorService, converter);
        startTatuador();
    }

    @Test
    void quandoBuscarTatuadorDeUmEstudioEntaoRetornaTatuadorDTO() {
        when(tatuadorService.buscaTatuadorNoEstudio(estudio.getId(), TATUADOR_ID)).thenReturn(tatuador);
        when(converter.paraDTO(tatuador)).thenReturn(tatuadorDTO);

        TatuadorDTO response = controller.buscarTatuador(estudio.getId(), TATUADOR_ID);

        assertNotNull(response);
        assertEquals(TatuadorDTO.class, response.getClass());
        assertEquals(TATUADOR_ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(tatuador.getTempoExperiencia(), response.getTempoExperiencia());
        assertEquals(avaliacao, response.getAvaliacao());

        verify(tatuadorService, times(1)).buscaTatuadorNoEstudio(estudio.getId(), TATUADOR_ID);
        verify(converter, times(1)).paraDTO(tatuador);

    }

    @Test
    void quandoListarOsTatuadoresDeUmEstudioEntaoRetornaTodosTatuadores() {
        when(tatuadorService.listarTodosTatuadoresDoEstudio(estudio.getId())).thenReturn(List.of(tatuador));
        when(converter.paraDTOLista(any())).thenReturn(List.of(tatuadorDTO));

        List<TatuadorDTO> response = controller.listar(estudio.getId());

        assertNotNull(response);

        assertEquals(TATUADOR_ID, response.get(0).getId());
        assertEquals(NOME, response.get(0).getNome());
        assertEquals(avaliacao, response.get(0).getAvaliacao());

    }

    @Test
    void quandoCadastrarUmTatuadoremUmEstudioEntaoRetornaOTatuadorDTO() {
        when(convertido.paraModelo(tatuadorInput)).thenReturn(tatuador);
        when(tatuadorService.cadastra(estudio.getId(), tatuadorInput)).thenReturn(tatuador);
        when(converter.paraDTO(tatuador)).thenReturn(tatuadorDTO);

        TatuadorDTO response = controller.cadastrarTatuadorEmEstudio(estudio.getId(), tatuadorInput);

        assertNotNull(response);
        assertEquals(TatuadorDTO.class, response.getClass());
        assertEquals(TATUADOR_ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(avaliacao, response.getAvaliacao());
    }

    @Test
    void quandoAtualizarUmTatuadorEntaoRetornaOTatuadorDTO() {
        when(tatuadorService.atualiza(estudio.getId(), TATUADOR_ID, tatuadorInput)).thenReturn(tatuador);
        when(converter.paraDTO(tatuador)).thenReturn(tatuadorDTO);

        TatuadorDTO response = controller.atualizarTatuador(estudio.getId(), TATUADOR_ID, tatuadorInput);

        assertNotNull(response);
        assertEquals(TatuadorDTO.class, response.getClass());
        assertEquals(TATUADOR_ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(avaliacao, response.getAvaliacao());

        verify(tatuadorService, times(1)).atualiza(estudio.getId(), TATUADOR_ID, tatuadorInput);
        verify(converter, times(1)).paraDTO(tatuador);
    }

    @Test
    void quandoAtivarUmTatuadorEntaoRetorna() {
        //TODO testar o endpoint de ativar e inativar
    }

    private void startTatuador() {
        especialidades.add(Especialidade.AQUARELA);
        especialidades.add(Especialidade.BLACKWORK);
        especialidades.add(Especialidade.REALISMO);
        avaliacao = new BigDecimal("4.5");

        Estado estado = new Estado(1L, "São Paulo");
        Cidade cidade = new Cidade(1L, "São Paulo", estado);
        Endereco endereco = new Endereco("01234-567", "Rua Principal", "123", "Apto 1", "Centro", cidade);
        estudio = new Estudio(1L, "Meu Estúdio", "123456789", "estudio@exemplo.com", "12.345.678/0001-90", "Razão Social", "instagram.com/estudio", endereco);

        Item item1 = new Item();
        item1.setId(1L);
        item1.setNome("Item 1");

        Item item2 = new Item();
        item2.setId(2L);
        item2.setNome("Item 2");

        estudio.getEstoque().add(item1);
        estudio.getEstoque().add(item2);

        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Cliente 1");

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Cliente 2");

        estudio.getClientes().add(cliente1);
        estudio.getClientes().add(cliente2);

        tatuador = new Tatuador(TATUADOR_ID, NOME, TEMPO_EXPERIENCIA, avaliacao, true);
        tatuadorInput = new TatuadorInput("Jorge", TEMPO_EXPERIENCIA, avaliacao);
        tatuadorDTO = new TatuadorDTO(TATUADOR_ID, NOME, TEMPO_EXPERIENCIA, avaliacao, true);

        tatuador.setEspecialidades(especialidades);
        estudio.associarTatuador(tatuador);
    }
}