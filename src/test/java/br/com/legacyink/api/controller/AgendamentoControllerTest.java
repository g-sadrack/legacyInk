package br.com.legacyink.api.controller;

import br.com.legacyink.api.dto.AgendamentoDTO;
import br.com.legacyink.api.dto.ClienteDTO;
import br.com.legacyink.api.dto.TatuagemDTO;
import br.com.legacyink.api.dto.input.AgendamentoInput;
import br.com.legacyink.api.dto.resumo.ClienteResumo;
import br.com.legacyink.api.dto.resumo.EnderecoResumoDTO;
import br.com.legacyink.api.dto.resumo.TatuagemResumo;
import br.com.legacyink.api.dtoconverter.AgendamentoDTOConverter;
import br.com.legacyink.domain.model.*;
import br.com.legacyink.domain.model.enums.Cor;
import br.com.legacyink.domain.model.enums.Especialidade;
import br.com.legacyink.domain.model.enums.Sexo;
import br.com.legacyink.domain.model.enums.StatusAgendamento;
import br.com.legacyink.domain.service.AgendamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class AgendamentoControllerTest {

    public static final long AGENDAMENTO_ID = 1L;
    private Agendamento agendamento;
    private AgendamentoDTO agendamentoDTO;
    private AgendamentoInput agendamentoInput;
    private Tatuador tatuador;
    private Estudio estudio;

    @Mock
    private AgendamentoService agendamentoService;
    @Mock
    private AgendamentoDTOConverter converter;
    @Mock
    private AgendamentoController controller;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new AgendamentoController(agendamentoService, converter);
        startAgendamento();
    }

    @Test
    void quandolistarAgendamentosEntaoRetornaListaDeAgendamentosDTO() {
        when(agendamentoService.listar(estudio.getId(), tatuador.getId())).thenReturn(List.of(agendamento));
        when(converter.paraDTO(agendamento)).thenReturn(agendamentoDTO);
        List<AgendamentoDTO> agendamentos = controller.listarAgendamentos(estudio.getId(), tatuador.getId());

        assertNotNull(agendamentos);

        verify(agendamentoService, times(1)).listar(1L, 1L);
    }

    @Test
    void quandoBuscarEntaoRetornaAgendamento() {
        when(agendamentoService.validaAgendamentoOuErro(AGENDAMENTO_ID)).thenReturn(agendamento);
        when(converter.paraDTO(agendamento)).thenReturn(agendamentoDTO);

        AgendamentoDTO agendamentoDTO = controller.buscar(AGENDAMENTO_ID);

        assertEquals(AgendamentoDTO.class, agendamentoDTO.getClass());
        assertEquals(AGENDAMENTO_ID, agendamento.getId());

        verify(agendamentoService, times(1)).validaAgendamentoOuErro(AGENDAMENTO_ID);
        verify(converter, times(1)).paraDTO(agendamento);

    }

    @Test
    void quandoRealizarAgendamentoEntaoAgendar() {
        when(agendamentoService.agendar(estudio.getId(), tatuador.getId(), agendamentoInput)).thenReturn(agendamento);
        when(converter.paraDTO(agendamento)).thenReturn(agendamentoDTO);

        AgendamentoDTO response = controller.agendar(estudio.getId(), tatuador.getId(), agendamentoInput);

        assertNotNull(response);
        assertEquals(AgendamentoDTO.class, response.getClass());
        assertEquals(AGENDAMENTO_ID, response.getId());

        verify(agendamentoService, times(1)).agendar(estudio.getId(), tatuador.getId(), agendamentoInput);
        verify(converter, times(1)).paraDTO(agendamento);

    }

    @Test
    void quandoAlterarAgendamentoEntaoRetornarAgendamentoAlterado() {
        when(agendamentoService.alterarAgendamento(estudio.getId(), tatuador.getId(), agendamento.getId(), agendamentoInput)).thenReturn(agendamento);
        when(converter.paraDTO(agendamento)).thenReturn(agendamentoDTO);

        AgendamentoDTO response = controller.alterar(estudio.getId(), tatuador.getId(), agendamento.getId(), agendamentoInput);

        assertNotNull(response);
        assertEquals(AgendamentoDTO.class, response.getClass());
        assertEquals(AGENDAMENTO_ID, response.getId());

        verify(agendamentoService, times(1)).alterarAgendamento(estudio.getId(), tatuador.getId(), agendamento.getId(), agendamentoInput);
        verify(converter, times(1)).paraDTO(agendamento);

    }

    @Test
    void quandoDesagendarEntaoDesagendar() {
        controller.desagendar(estudio.getId(), tatuador.getId(), agendamento.getId());

        verify(agendamentoService, times(1)).desagendar(estudio.getId(), tatuador.getId(), agendamento.getId());
    }

    void startAgendamento() {
        ArrayList<Especialidade> especialidades = new ArrayList<>();
        especialidades.add(Especialidade.AQUARELA);
        especialidades.add(Especialidade.BLACKWORK);
        especialidades.add(Especialidade.REALISMO);

        tatuador = new Tatuador(1L, "Maria", especialidades, 1, BigDecimal.valueOf(4.5));

        estudio = new Estudio(1L, "Meu Estúdio", "123456789", "estudio@exemplo.com", "12.345.678/0001-90", "Razão Social", "instagram.com/estudio", new Endereco());
        estudio.associarTatuador(tatuador);

        Cliente cliente = new Cliente(1L, "Jorge", 72, Sexo.MASCULINO, "Jorge@gmail.com", "996812321", LocalDate.of(1980, 11, 20), new Endereco());
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Jorge", 72, Sexo.MASCULINO, "Jorge@gmail.com", "996812321", LocalDate.of(1980, 11, 20), new EnderecoResumoDTO());

        Tatuagem tatuagem = new Tatuagem(1L, "Tatuagem", 20, Cor.PRETO_E_BRANCO, "Costas", "https://asjndaskdasn.png", BigDecimal.valueOf(100.0));
        TatuagemDTO tatuagemDTO = new TatuagemDTO(1L, "Tatuagem", 20, Cor.PRETO_E_BRANCO, "Costas", "https://asjndaskdasn.png", BigDecimal.valueOf(100.0));

        agendamento = new Agendamento(1L, cliente, tatuagem, StatusAgendamento.COFIRMADO, LocalDateTime.now(), OffsetDateTime.now(), OffsetDateTime.now());
        agendamentoDTO = new AgendamentoDTO(
                1L,
                new ClienteResumo("Jorge", "Jorge@gmail.com", "996812321"),
                new TatuagemResumo("Tatuagem de dragão", 20, BigDecimal.valueOf(350.0),Cor.PRETO_E_BRANCO, "Costas", "https://asjndaskdasn.png"),
                StatusAgendamento.COFIRMADO);
        agendamentoInput = new AgendamentoInput(clienteDTO, tatuagemDTO, StatusAgendamento.COFIRMADO, LocalDateTime.now());

        tatuador.marcarAgendamento(agendamento);
    }
}