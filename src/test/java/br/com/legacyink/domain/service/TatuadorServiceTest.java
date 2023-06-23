package br.com.legacyink.domain.service;

import br.com.legacyink.api.domainconverter.TatuadorConvertido;
import br.com.legacyink.api.dto.input.TatuadorInput;
import br.com.legacyink.domain.exception.EstudioNaoEncontradoException;
import br.com.legacyink.domain.exception.TatuadorNaoEncontradoException;
import br.com.legacyink.domain.model.*;
import br.com.legacyink.domain.model.enums.Especialidade;
import br.com.legacyink.domain.repository.TatuadorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TatuadorServiceTest {

    public static final long TATUADOR_ID = 1L;
    public static final String MSG_ESTUDIO_NAO_ENCONTRADO = String.format("Estudio não encontrado: %d", TATUADOR_ID);
    public static final String NOME = "João";
    public static final int TEMPO_EXPERIENCIA = 10;
    private Tatuador tatuador;
    private TatuadorInput tatuadorInput;
    private Estudio estudio;
    private List<Especialidade> especialidades;
    private BigDecimal avaliacao;

    TatuadorService tatuadorService;
    @Mock
    TatuadorRepository tatuadorRepository;
    @Mock
    EstudioService estudioService;
    @Mock
    TatuadorConvertido convertido;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tatuadorService = new TatuadorService(tatuadorRepository, estudioService, convertido);
        startTatuador();
    }

    @Test
    void quandoBuscarTatuadorNoEstudioEntaoRetornaTatuador() {
        when(estudioService.buscaEstudioOuErro(anyLong())).thenReturn(estudio);

        Tatuador tatuador = tatuadorService.buscaTatuadorNoEstudio(estudio.getId(), TATUADOR_ID);

        assertNotNull(tatuador);
        assertEquals(Tatuador.class, tatuador.getClass());
        assertEquals(TATUADOR_ID, tatuador.getId());
    }

    @Test
    void quandoBuscaTatuadorInexistenteEmEstudioEntaoRetornaExcecao() {
        when(estudioService.buscaEstudioOuErro(anyLong())).thenReturn(estudio);

        TatuadorNaoEncontradoException ex = assertThrows(TatuadorNaoEncontradoException.class,
                () -> tatuadorService.buscaTatuadorNoEstudio(estudio.getId(), 3L));

        assertEquals(String.format("O Tatuador de ID %d não consta no sistema ou não faz parte desse estudio", 3L), ex.getMessage());
    }

    @Test
    void quandoBuscaTatuadorEmEstudioInexistenteEntaoRetornaExcecao() {
        when(estudioService.buscaEstudioOuErro(anyLong())).thenThrow(new EstudioNaoEncontradoException(MSG_ESTUDIO_NAO_ENCONTRADO));

        EstudioNaoEncontradoException exception = assertThrows(EstudioNaoEncontradoException.class, () ->
                estudioService.buscaEstudioOuErro(2L));

        assertEquals(MSG_ESTUDIO_NAO_ENCONTRADO, exception.getMessage());
    }

    @Test
    void quadoListarTatuadoresDoEstudioEntaoRetornaListaDeTatuadores() {
        when(estudioService.buscaEstudioOuErro(anyLong())).thenReturn(estudio);
        List<Tatuador> tatuadores = tatuadorService.listarTatuadores(estudio.getId());

        assertNotNull(tatuadores);
        assertEquals(TATUADOR_ID, tatuadores.get(0).getId());
        assertEquals(NOME, tatuadores.get(0).getNome());
        assertEquals(especialidades, tatuadores.get(0).getEspecialidades());
        assertEquals(TEMPO_EXPERIENCIA, tatuadores.get(0).getTempoExperiencia());
        assertEquals(avaliacao, tatuadores.get(0).getAvaliacao());
    }

    @Test
    void quandoCadastrarTatuadorEntaoRetoneraTatuador() {
        when(estudioService.buscaEstudioOuErro(estudio.getId())).thenReturn(estudio);
        when(tatuadorRepository.save(tatuador)).thenReturn(tatuador);

        Tatuador tatuadorSalvo = tatuadorService.cadastra(estudio.getId(), tatuador);

        assertNotNull(tatuadorSalvo);
        assertEquals(Tatuador.class, tatuadorSalvo.getClass());
        assertEquals(tatuador.getId(), tatuadorSalvo.getId());
    }

    @Test
    void quandoAtualizarTatuadorEntaoRetoneraTatuador() {

        when(estudioService.buscaEstudioOuErro(estudio.getId())).thenReturn(estudio);

        // Configuração do mock do TatuadorRepository
        when(tatuadorRepository.findById(estudio.getId())).thenReturn(Optional.of(tatuador));
        when(tatuadorRepository.save(tatuador)).thenReturn(tatuador);

        // Chamada do método atualiza
        Tatuador tatuadorAtualizado = tatuadorService.atualiza(estudio.getId(), tatuador.getId(), tatuadorInput);

        // Verificações
        verify(estudioService).buscaEstudioOuErro(estudio.getId());
        verify(tatuadorRepository).findById(tatuador.getId());
        verify(tatuadorRepository).save(tatuador);
        assertEquals(tatuador, tatuadorAtualizado);
    }

    public void startTatuador() {
        especialidades = new ArrayList<>();
        especialidades.add(Especialidade.AQUARELA);
        especialidades.add(Especialidade.BLACKWORK);
        especialidades.add(Especialidade.REALISMO);
        avaliacao = new BigDecimal("4.5");

        Estado estado = new Estado(1L, "São Paulo");
        Cidade cidade = new Cidade(1L, "São Paulo", estado);
        Endereco endereco = new Endereco("01234-567", "Rua Principal", "123", "Apto 1", "Centro", cidade);
        estudio = new Estudio(1L, "Meu Estúdio", "123456789", "estudio@exemplo.com", "12.345.678/0001-90", "Razão Social", "instagram.com/estudio", LocalDate.now(), endereco);

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

        tatuador = new Tatuador(TATUADOR_ID, NOME, especialidades, TEMPO_EXPERIENCIA, avaliacao);
        tatuadorInput = new TatuadorInput( "Jorge",  20, avaliacao);
        estudio.associarTatuador(tatuador);
    }
}