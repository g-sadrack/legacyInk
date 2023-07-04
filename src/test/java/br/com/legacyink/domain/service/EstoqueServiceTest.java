package br.com.legacyink.domain.service;

import br.com.legacyink.api.domainconverter.ItemConvertido;
import br.com.legacyink.api.dto.input.ItemInput;
import br.com.legacyink.domain.exception.ItemNaoEncontradoException;
import br.com.legacyink.domain.model.*;
import br.com.legacyink.domain.repository.EstoqueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class EstoqueServiceTest {
    public static final long ID = 1L;
    public static final String NOME = "Tinta";
    public static final String DESCRICAO = "Tinta preta para tatuagem";
    public static final int QUANTIDADE = 50;
    private Estudio estudio;
    private Item item;

    private ItemInput itemInput;

    @Mock
    EstoqueService estoqueService;
    @Mock
    EstoqueRepository estoqueRepository;
    @Mock
    ItemConvertido convertido;
    @Mock
    EstudioService estudioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        estoqueService = new EstoqueService(estoqueRepository, estudioService, convertido);
        startEstoque();
    }

    @Test
    void quandoValidaExistenciaDeProdutoEntaoRetorna() {
        when(estoqueRepository.findById(anyLong())).thenReturn(estudio.getEstoque().stream().findFirst());
        Item item = estoqueService.validaProduto(ID);
        assertNotNull(item);
    }

    @Test
    void quandoNaoValidaExistenciaDeProdutoEntaoRetornaErro() {
        when(estoqueRepository.findById(anyLong())).thenThrow(new ItemNaoEncontradoException(ID));

        ItemNaoEncontradoException exception = assertThrows(ItemNaoEncontradoException.class, () -> estoqueService.validaProduto(ID));

        assertNotNull(exception);
        assertEquals(String.format("Não existe um cadastro do item com o código de id: %d", ID), exception.getMessage());

    }

    @Test
    void quandoBuscaProdutoEmEstoqueDoEstudioEntaoRetornaListaDeProduto() {
        when(estudioService.buscaEstudioOuErro(anyLong())).thenReturn(estudio);
        List<Item> estoque = estoqueService.listar(estudio.getId());

        assertEquals(1, estoque.size());
        assertEquals(estoque, estudio.getEstoque());
    }

    @Test
    void quandoBuscaProdutoEmEstoqueEntaoRetornaProduto() {
        when(estudioService.buscaEstudioOuErro(anyLong())).thenReturn(estudio);
        Item item = estoqueService.buscaProdutoEmEstoqueDoEstudio(1L, ID);

        assertNotNull(item);
        assertEquals(ID, item.getId());
        assertEquals(NOME, item.getNome());
        assertEquals(DESCRICAO, item.getDescricao());
        assertEquals(QUANTIDADE, item.getQuantidade());
        assertEquals(LocalDateTime.of(2023, 6, 19, 10, 0, 0), item.getDataCadastro());
        assertEquals(LocalDateTime.of(2023, 6, 19, 14, 30, 0), item.getDataAtualizacao());
    }

    @Test
    void quandoAssociarItemAoEstoqueEntaoRetornaItem() {
        // Configuração dos comportamentos esperados
        when(convertido.paraModelo(itemInput)).thenReturn(item);
        when(estudioService.buscaEstudioOuErro(estudio.getId())).thenReturn(estudio);
        when(estoqueRepository.save(item)).thenReturn(item);

        // Execução do método
        Item resultado = estoqueService.associaItemAoEstoqueEstudio(estudio.getId(), itemInput);

        // Verificações
        assertNotNull(resultado);
        assertEquals(item, resultado);
        assertTrue(estudio.getEstoque().contains(item));
        verify(estoqueRepository, times(1)).save(item);
    }

    @Test
    void quandoAssociarItemInexistenteAoEstoqueEntaoRetornaErro() {
        estudio.setId(estudio.getId());

        // Configuração dos comportamentos esperados
        when(convertido.paraModelo(itemInput)).thenReturn(item);
        when(estudioService.buscaEstudioOuErro(estudio.getId())).thenReturn(estudio);
        when(estoqueRepository.save(any())).thenThrow(new ItemNaoEncontradoException(item.getId()));

        // Execução do método e verificação da exceção
        ItemNaoEncontradoException exception = assertThrows(ItemNaoEncontradoException.class, () -> estoqueService.associaItemAoEstoqueEstudio(estudio.getId(), itemInput));

        assertEquals(ItemNaoEncontradoException.class, exception.getClass());
        assertEquals(String.format("Não existe um cadastro do item com o código de id: %d", item.getId()), exception.getMessage());
    }

    @Test
    void quandoAlterarItemDoEstoqueEntaoRetornaItemAtualizado() {
        when(estoqueRepository.findById(item.getId())).thenReturn(Optional.of(item));
        when(estoqueRepository.save(item)).thenReturn(item);

        Item response = estoqueService.alterarItem(item.getId(), itemInput);

        assertNotNull(response);
        assertEquals(Item.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(DESCRICAO, response.getDescricao());
        assertEquals(QUANTIDADE, response.getQuantidade());
        assertEquals(LocalDateTime.of(2023, 6, 19, 10, 0, 0), response.getDataCadastro());
        assertEquals(LocalDateTime.of(2023, 6, 19, 14, 30, 0), response.getDataAtualizacao());

    }

    @Test
    void quandoSalvarItemAoSistemaEntaoRetornaOItem() {
        when(estoqueRepository.findById(anyLong())).thenReturn(Optional.ofNullable(item));
        Item itemNovo = estoqueService.validaProduto(1L);

        assertNotNull(itemNovo);
    }


    @Test
    void quandoDeletarItemDoEstoqueEntaoRetorna() {
        // Configuração dos comportamentos esperados
        when(estudioService.buscaEstudioOuErro(anyLong())).thenReturn(estudio);
        when(estoqueRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(item));
        Item itemNovo = estoqueService.validaProduto(1L);
        // Execução do método
        estoqueService.removerItem(1L, itemNovo.getId());

        // Verificações
        assertFalse(estudio.getEstoque().contains(item));
    }

    @Test
    void quandoDeletarItemInexistenteDoEstoqueEntaoRetornaErro() {
        // Configuração dos comportamentos esperados
        when(estudioService.buscaEstudioOuErro(anyLong())).thenReturn(estudio);
        when(estoqueRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        // Execução do método e verificação da exceção
        assertThrows(ItemNaoEncontradoException.class, () -> estoqueService.removerItem(estudio.getId(), 3L));
    }

    void startEstoque() {

        item = new Item(ID, NOME, DESCRICAO, QUANTIDADE,
                LocalDateTime.of(2023, 6, 19, 10, 0, 0),
                LocalDateTime.of(2023, 6, 19, 14, 30, 0));

        itemInput = new ItemInput("Agulha", "Agulha de ponta fina para contorno", 100);

        Estado estado = new Estado(1L, "São Paulo");
        Cidade cidade = new Cidade(1L, "São Paulo", estado);
        Endereco endereco = new Endereco("01234-567", "Rua Principal", "123", "Apto 1", "Centro", cidade);
        estudio = new Estudio(1L, "Meu Estúdio", "123456789", "estudio@exemplo.com", "12.345.678/0001-90", "Razão Social", "instagram.com/estudio", endereco);

        estudio.adicionarItem(item);

    }

}