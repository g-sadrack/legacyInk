package br.com.legacyink.domain.service;

import br.com.legacyink.api.domainconverter.AgendamentoConvertido;
import br.com.legacyink.api.dto.ClienteDTO;
import br.com.legacyink.api.dto.TatuagemDTO;
import br.com.legacyink.api.dto.input.AgendamentoInput;
import br.com.legacyink.api.dto.input.ClienteIdInput;
import br.com.legacyink.api.dto.input.TatuagemInput;
import br.com.legacyink.api.dto.resumo.EnderecoResumoDTO;
import br.com.legacyink.domain.exception.AgendamentoNaoEncontradoException;
import br.com.legacyink.domain.model.*;
import br.com.legacyink.domain.model.enums.Cor;
import br.com.legacyink.domain.model.enums.Especialidade;
import br.com.legacyink.domain.model.enums.Sexo;
import br.com.legacyink.domain.model.enums.StatusAgendamento;
import br.com.legacyink.domain.repository.AgendamentoRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AgendamentoServiceTest {

    public static final long AGENDAMENTO_ID = 1L;
    private Agendamento agendamento;
    private AgendamentoInput agendamentoInput;
    private Tatuador tatuador;
    private Estudio estudio;

    @Mock
    private AgendamentoRepository agendamentoRepository;
    @Mock
    private TatuadorService tatuadorService;
    @Mock
    private AgendamentoConvertido convertido;
    @Mock
    private AgendamentoService agendamentoService;
    @Mock
    private TatuagemService tatuagemService;
    @Mock
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        agendamentoService = new AgendamentoService(agendamentoRepository, tatuadorService, convertido, tatuagemService, clienteService);
        startAgendamento();
    }

    @Test
    void quandoValidaEnderecoEntaoRetornaAgendamento() {
        when(agendamentoRepository.findById(1L)).thenReturn(Optional.of(agendamento));

        Agendamento response = agendamentoService.validaAgendamentoOuErro(1L);

        assertNotNull(response);

        assertEquals(Agendamento.class, response.getClass());
        assertEquals(1L, response.getId());
    }

    @Test
    void quandoListarAgendamentosDoTatuadorEntaoRetornaListaDeAgendamentos() {
        when(tatuadorService.buscaTatuadorNoEstudio(1L, 1L)).thenReturn(tatuador);

        List<Agendamento> response = agendamentoService.listar(1L, 1L);

        assertNotNull(response);

        assertEquals(Agendamento.class, response.get(0).getClass());
        assertEquals(1L, response.get(0).getId());
        assertEquals(1L, response.get(0).getCliente().getId());
        assertEquals(1L, response.get(0).getTatuagem().getId());
        assertEquals(StatusAgendamento.CONFIRMADO, response.get(0).getStatus());

    }

    @Test
    void quandoAgendarEntaoRetornarOAgendamento() {
        when(tatuadorService.buscaTatuadorNoEstudio(estudio.getId(), tatuador.getId())).thenReturn(tatuador);
        when(convertido.paraModelo(agendamentoInput)).thenReturn(agendamento);
        when(agendamentoRepository.save(agendamento)).thenReturn(agendamento);

        Agendamento response = agendamentoService.agendar(estudio.getId(), tatuador.getId(), agendamentoInput);

        assertNotNull(response);
    }

    @Test
    void quandoAlterarUmAgendamentoAlterarEntaoRetornarAgendamento() {
        when(tatuadorService.buscaTatuadorNoEstudio(estudio.getId(), tatuador.getId())).thenReturn(tatuador);
        Agendamento agendamentoAlterado = agendamentoService.alterarAgendamento(estudio.getId(), tatuador.getId(), agendamento.getId(), agendamentoInput);

        assertEquals(agendamento, agendamentoAlterado);
    }

    @Test
    void quandoAlterarUmAgendamentoAlterarEntaoRetornarAgendament() {
        when(tatuadorService.buscaTatuadorNoEstudio(estudio.getId(), tatuador.getId())).thenReturn(tatuador);

        AgendamentoNaoEncontradoException exception = assertThrows(AgendamentoNaoEncontradoException.class,
                () -> agendamentoService.alterarAgendamento(1L, 1L, 2L, agendamentoInput));

        assertEquals(AgendamentoNaoEncontradoException.class, exception.getClass());

    }


    @Test
    void quandoDesagendarAgendamentoExistenteEntaoSetarStatusCanceladoEDesmarcarAgendamento() {
        when(tatuadorService.buscaTatuadorNoEstudio(estudio.getId(), tatuador.getId())).thenReturn(tatuador);
        agendamentoService.desagendar(estudio.getId(), tatuador.getId(), 1L);

        //TODO MELHORIA > assertEquals(StatusAgendamento.CANCELADO, agendamento.getStatus());
    }

    @Test
    void quandoDesagendarAgendamentoInexistenteEntaoRetornarErro() {
        when(tatuadorService.buscaTatuadorNoEstudio(estudio.getId(), tatuador.getId()))
                .thenThrow(new AgendamentoNaoEncontradoException(agendamento.getId()));

        AgendamentoNaoEncontradoException exception = assertThrows(
                AgendamentoNaoEncontradoException.class, () -> {
                    agendamentoService.desagendar(estudio.getId(), tatuador.getId(), AGENDAMENTO_ID);
                });

        assertEquals(AgendamentoNaoEncontradoException.class, exception.getClass());
        assertEquals(String.format("O Agendamento de id: %d não conta no sistema", AGENDAMENTO_ID), exception.getMessage());
    }

    void startAgendamento() {
        ArrayList<Especialidade> especialidades = new ArrayList<>();
        especialidades.add(Especialidade.AQUARELA);
        especialidades.add(Especialidade.BLACKWORK);
        especialidades.add(Especialidade.REALISMO);

        tatuador = new Tatuador(1L, "Maria", 1, BigDecimal.valueOf(4.5), true);
        tatuador.setEspecialidades(especialidades);

        estudio = new Estudio(1L, "Meu Estúdio", "123456789", "estudio@exemplo.com", "12.345.678/0001-90", "Razão Social", "instagram.com/estudio", new Endereco());
        estudio.associarTatuador(tatuador);

        Cliente cliente = new Cliente(1L, "Jorge", 72, Sexo.MASCULINO, "Jorge@gmail.com", "996812321", LocalDate.of(1980, 11, 20), new Endereco());
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Jorge", 72, Sexo.MASCULINO, "Jorge@gmail.com", "996812321", LocalDate.of(1980, 11, 20), new EnderecoResumoDTO());

        Tatuagem tatuagem = new Tatuagem(1L, "Tatuagem", 20, Cor.PRETO_E_BRANCO, "Costas", "https://asjndaskdasn.png", BigDecimal.valueOf(100.0));
        TatuagemDTO tatuagemDTO = new TatuagemDTO(1L, "Tatuagem", 20, Cor.PRETO_E_BRANCO, "Costas", "https://asjndaskdasn.png", BigDecimal.valueOf(100.0));

        agendamento = new Agendamento(1L, cliente, tatuagem, StatusAgendamento.CONFIRMADO, OffsetDateTime.now(), OffsetDateTime.now(), OffsetDateTime.now());
        agendamentoInput = new AgendamentoInput(new ClienteIdInput(1L), new TatuagemInput(), StatusAgendamento.CONFIRMADO, LocalDateTime.now());

        tatuador.marcarAgendamento(agendamento);
    }

}