package br.com.legacyink.domain.service;

import br.com.legacyink.domain.exception.EntidadeEmUsoException;
import br.com.legacyink.domain.exception.EstudioNaoEncontradoException;
import br.com.legacyink.domain.model.*;
import br.com.legacyink.domain.repository.EstudioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class EstudioServiceTest {

    public static final long ESTUDIO_ID = 1L;
    public static final String NOME_DO_ESTUDIO = "Legacyink";
    public static final String TELEFONE = "123456789";
    public static final String EMAIL = "estudio@exemplo.com";
    public static final String CNPJ = "12.345.678/0001-90";
    public static final String RAZAO_SOCIAL = "Legacy ink LTDA";
    public static final String REDES_SOCIAIS = "instagram.com/estudio";
    private Estudio estudio;
    private EstudioService estudioService;
    @Mock
    private EstudioRepository estudioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        estudioService = new EstudioService(estudioRepository);
        estudioStart();
    }

    @Test
    void quandoBuscaEstudioEntaoRetorneEstudio() {
        when(estudioRepository.findById(anyLong())).thenReturn(Optional.of(estudio));
        Estudio response = estudioService.buscaEstudioOuErro(ESTUDIO_ID);

        assertNotNull(response);
        assertEquals(ESTUDIO_ID, response.getId());
        assertEquals(NOME_DO_ESTUDIO, response.getNome());
        assertEquals(TELEFONE, response.getTelefone());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(CNPJ, response.getCnpj());
        assertEquals(RAZAO_SOCIAL, response.getRazaoSocial());
        assertEquals(REDES_SOCIAIS, response.getRedesSociais());
    }

    @Test
    void quandoBuscaEstudioNaoExistenteEntaoRetorneErro() {
        when(estudioRepository.findById(2L)).thenThrow(new EstudioNaoEncontradoException(2L));
        EstudioNaoEncontradoException exception = assertThrows(
                EstudioNaoEncontradoException.class, () -> estudioService.buscaEstudioOuErro(2L));

        assertEquals(String.format("Não existe um cadastro de estudio com o código de id: %d", 2L), exception.getMessage());
    }

    @Test
    void listarEstudios() {
        when(estudioRepository.findAll()).thenReturn(List.of(estudio));
        List<Estudio> response = estudioService.listarEstudios();

        assertNotNull(response);
        assertEquals(ESTUDIO_ID, response.get(0).getId());
        assertEquals(NOME_DO_ESTUDIO, response.get(0).getNome());
        assertEquals(TELEFONE, response.get(0).getTelefone());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(CNPJ, response.get(0).getCnpj());
        assertEquals(RAZAO_SOCIAL, response.get(0).getRazaoSocial());
        assertEquals(REDES_SOCIAIS, response.get(0).getRedesSociais());
    }

    @Test
    void quandoAdicionarEstudioEntaoRetornaEstudio() {
        when(estudioRepository.save(any())).thenReturn(estudio);

        Estudio response = estudioService.adicionar(estudio);

        assertNotNull(response);
    }

    @Test
    void quandoRemoverEstudioComEstoqueVazioTatuadoresVaziosClientesVaziosEntaoRemoveEstudio() {
        // Dados de exemplo
        estudio.setEstoque(Collections.emptyList());
        estudio.setTatuadores(Collections.emptyList());
        estudio.setClientes(Collections.emptyList());

        // Configuração do mock do EstudioRepository
        when(estudioRepository.findById(anyLong())).thenReturn(Optional.of(estudio));

        // Chamada do método remover
        estudioService.remover(ESTUDIO_ID);

        // Verificações
        verify(estudioRepository).deleteById(ESTUDIO_ID);
    }

    @Test
     void quandoRemoverEstudioComEstoqueNaoVazioEntaoLancaExcecao() {
        // Dados de exemplo
        estudio.setEstoque(Collections.singletonList(new Item()));

        // Configuração do mock do EstudioRepository
        when(estudioRepository.findById(anyLong())).thenReturn(Optional.of(estudio));

        // Verifica se a exceção EntidadeEmUsoException é lançada ao chamar o método remover
        EntidadeEmUsoException exception = assertThrows(EntidadeEmUsoException.class, () -> estudioService.remover(1L));
        assertEquals("O Estudio possui itens em estoque associado", exception.getMessage());
    }

    @Test
    void quandoRemoverEstudioComTatuadoresNaoVaziosEntaoLancaExcecao() {
        // Dados de exemplo
        estudio.setTatuadores(Collections.singletonList(new Tatuador()));

        // Configuração do mock do EstudioRepository
        when(estudioRepository.findById(anyLong())).thenReturn(Optional.of(estudio));

        // Verifica se a exceção EntidadeEmUsoException é lançada ao chamar o método remover
        EntidadeEmUsoException exception = assertThrows(EntidadeEmUsoException.class, () -> estudioService.remover(ESTUDIO_ID));

        assertEquals("O Estudio possui tatuadores associados", exception.getMessage());
    }

    @Test
    void quandoRemoverEstudioComClientesNaoVaziosEntaoLancaExcecao() {
        // Dados de exemplo
        estudio.setClientes(Collections.singletonList(new Cliente()));

        // Configuração do mock do EstudioRepository
        when(estudioRepository.findById(ESTUDIO_ID)).thenReturn(Optional.of(estudio));

        // Verifica se a exceção EntidadeEmUsoException é lançada ao chamar o método remover
        EntidadeEmUsoException exception = assertThrows(EntidadeEmUsoException.class, () -> estudioService.remover(ESTUDIO_ID));

        assertEquals("O Estudio possui clientes associados", exception.getMessage());
    }

    @Test
    void quandoRemoverEstudioInexistenteEntaoLancaExcecao() {
        // Dados de exemplo
        Long id = 2L;

        // Configuração do mock do EstudioRepository
        when(estudioRepository.findById(id)).thenReturn(Optional.empty());

        // Verifica se a exceção EstudioNaoEncontradoException é lançada ao chamar o método remover
        EstudioNaoEncontradoException exception = assertThrows(EstudioNaoEncontradoException.class, () -> estudioService
                .remover(id));

        assertEquals(String.format("Não existe um cadastro de estudio com o código de id: %d", id), exception.getMessage());
    }

    void estudioStart() {
        Estado estado = new Estado(1L, "São Paulo");
        Cidade cidade = new Cidade(1L, "São Paulo", estado);
        Endereco endereco = new Endereco("01234-567", "Rua Principal", "123", "Apto 1", "Centro", cidade);
        estudio = new Estudio(ESTUDIO_ID, NOME_DO_ESTUDIO, TELEFONE, EMAIL, CNPJ, RAZAO_SOCIAL, REDES_SOCIAIS, LocalDate.now(), endereco);
    }
}