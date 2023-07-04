package br.com.legacyink.api.controller;

import br.com.legacyink.api.dto.ItemDTO;
import br.com.legacyink.api.dto.input.ItemInput;
import br.com.legacyink.api.dtoconverter.ItemDTOConverter;
import br.com.legacyink.domain.model.*;
import br.com.legacyink.domain.service.EstoqueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class EstoqueControllerTest {
    public static final long ID = 1L;
    public static final String NOME = "Tinta";
    public static final String DESCRICAO = "Tinta preta para tatuagem";
    public static final Integer QUANTIDADE = 50;
    private Estudio estudio;
    private Item item;
    private ItemInput itemInput;
    private ItemDTO itemDTO;

    @Mock
    private EstoqueService estoqueService;
    @Mock
    private EstoqueController controller;
    @Mock
    private ItemDTOConverter converter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new EstoqueController(estoqueService, converter);
        startEstoque();
    }

    @Test
    void quandoListarOsItensDoEstoqueEntaoRetornarUmaListaDosItens() {
        when(estoqueService.listar(estudio.getId())).thenReturn(List.of(item));
        when(converter.paraDTO(item)).thenReturn(itemDTO);

        List<ItemDTO> itemDTOS = controller.listarItensDoEstoque(estudio.getId());

        assertNotNull(itemDTOS);

        verify(estoqueService, times(1)).listar(estudio.getId());
    }

    @Test
    void quandoBuscarUmItemEntaoRetorneOItem() {
        when(estoqueService.buscaProdutoEmEstoqueDoEstudio(ID, ID)).thenReturn(item);
        when(converter.paraDTO(item)).thenReturn(itemDTO);

        ItemDTO response = controller.buscarItemEmEstoque(estudio.getId(), item.getId());

        assertNotNull(response);
        assertEquals(ItemDTO.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(DESCRICAO, response.getDescricao());

        verify(estoqueService, times(1)).buscaProdutoEmEstoqueDoEstudio(ID, ID);
        verify(converter, times(1)).paraDTO(item);
    }

    @Test
    void quandoCadastrarUmItemEmAlgumEstoqueEntaoRetorneOItemDTO() {
        when(estoqueService.associaItemAoEstoqueEstudio(ID, itemInput)).thenReturn(item);
        when(converter.paraDTO(item)).thenReturn(itemDTO);

        ItemDTO response = controller.cadastrarItemAoEstoque(estudio.getId(), itemInput);

        assertNotNull(response);
        assertEquals(ItemDTO.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(DESCRICAO, response.getDescricao());
        assertEquals(QUANTIDADE, response.getQuantidade());

        verify(estoqueService, times(1)).associaItemAoEstoqueEstudio(ID, itemInput);
        verify(converter, times(1)).paraDTO(item);
    }

    @Test
    void quandoAlterarUmItemEntaoRetorneOItemAlterado() {
        when(estoqueService.alterarItem(ID, itemInput)).thenReturn(item);
        when(converter.paraDTO(item)).thenReturn(itemDTO);

        ItemDTO response = controller.alterarItemDoEstoque(ID, itemInput);

        assertNotNull(response);
        assertEquals(ItemDTO.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(DESCRICAO, response.getDescricao());

        verify(estoqueService, times(1)).alterarItem(ID, itemInput);
        verify(converter, times(1)).paraDTO(item);
    }

    @Test
    void quandoremoverItemDoEstoqueEntaoRemoverItem() {
        controller.removerItemDoEstoque(ID, ID);

        verify(estoqueService, times(1)).removerItem(ID, ID);
    }

    void startEstoque() {

        item = new Item(ID, NOME, DESCRICAO, QUANTIDADE, LocalDateTime.of(2023, 6, 19, 10, 0, 0), LocalDateTime.of(2023, 6, 19, 14, 30, 0));
        var item2 = new Item(2L, "Papel tolha", DESCRICAO, QUANTIDADE, LocalDateTime.of(2023, 6, 19, 10, 0, 0), LocalDateTime.of(2023, 6, 19, 14, 30, 0));
        var item3 = new Item(3L, "Piercing", DESCRICAO, QUANTIDADE, LocalDateTime.of(2023, 6, 19, 10, 0, 0), LocalDateTime.of(2023, 6, 19, 14, 30, 0));
        itemInput = new ItemInput("Agulha", "Agulha de ponta fina para contorno", 100);
        itemDTO = new ItemDTO(ID, NOME, DESCRICAO, QUANTIDADE);


        Estado estado = new Estado(1L, "São Paulo");
        Cidade cidade = new Cidade(1L, "São Paulo", estado);
        Endereco endereco = new Endereco("01234-567", "Rua Principal", "123", "Apto 1", "Centro", cidade);
        estudio = new Estudio(1L, "Meu Estúdio", "123456789", "estudio@exemplo.com", "12.345.678/0001-90", "Razão Social", "instagram.com/estudio", endereco);

        estudio.adicionarItem(item);
        estudio.adicionarItem(item2);
        estudio.adicionarItem(item3);

    }

}