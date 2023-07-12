package br.com.legacyink.api.controller;

import br.com.legacyink.api.dto.EstudioDTO;
import br.com.legacyink.api.dto.input.EnderecoInput;
import br.com.legacyink.api.dto.input.EstudioInput;
import br.com.legacyink.api.dtoconverter.EstudioDTOconverter;
import br.com.legacyink.domain.model.Cidade;
import br.com.legacyink.domain.model.Endereco;
import br.com.legacyink.domain.model.Estado;
import br.com.legacyink.domain.model.Estudio;
import br.com.legacyink.domain.service.EstudioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class EstudioControllerTest {

    public static final long ESTUDIO_ID = 1L;
    public static final String NOME_DO_ESTUDIO = "Legacyink";
    public static final String TELEFONE = "123456789";
    public static final String EMAIL = "estudio@exemplo.com";
    public static final String CNPJ = "12.345.678/0001-90";
    public static final String RAZAO_SOCIAL = "Legacy ink LTDA";
    public static final String REDES_SOCIAIS = "instagram.com/estudio";
    private Estudio estudio;
    private EstudioInput estudioInput;
    private EstudioDTO estudioDTO;
    @Mock
    private EstudioController controller;
    @Mock
    private EstudioService estudioService;
    @Mock
    private EstudioDTOconverter converter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new EstudioController(estudioService, converter);
        startEstudio();
    }

    @Test
    void quandoBuscarEstudioEntaoRetornaEstudio() {
        when(estudioService.buscaEstudioOuErro(ESTUDIO_ID)).thenReturn(estudio);
        when(converter.paraDTO(estudio)).thenReturn(estudioDTO);

        EstudioDTO response = controller.buscarEstudio(ESTUDIO_ID);

        assertNotNull(response);

        assertEquals(ESTUDIO_ID, response.getId());
        assertEquals(NOME_DO_ESTUDIO, response.getNome());
        assertEquals(TELEFONE, response.getTelefone());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(CNPJ, response.getCnpj());

        verify(estudioService, times(1)).buscaEstudioOuErro(ESTUDIO_ID);
        verify(converter, times(1)).paraDTO(estudio);

    }

    @Test
    void quandoListarUmaListaDeEstudiosEntaoRetornaListaEstudioDTO() {
        when(estudioService.listarEstudios()).thenReturn(List.of(estudio));
        when(converter.paraDTOLista(any())).thenReturn(List.of(estudioDTO));

        List<EstudioDTO> response = controller.listarEstudios();

        assertNotNull(response);

        assertEquals(1, response.size());

        assertEquals(ESTUDIO_ID, response.get(0).getId());
        assertEquals(NOME_DO_ESTUDIO, response.get(0).getNome());
        assertEquals(TELEFONE, response.get(0).getTelefone());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(CNPJ, response.get(0).getCnpj());

        verify(estudioService, times(1)).listarEstudios();
        verify(converter, times(1)).paraDTOLista(List.of(estudio));

    }

    @Test
    void quandoCadastrarEstudioEntaoRetornaEstudioDTO() {
        when(estudioService.cadastrarEstudio(any())).thenReturn(estudio);
        when(converter.paraDTO(estudio)).thenReturn(estudioDTO);

        EstudioDTO response = controller.cadastrarEstudio(estudioInput);

        assertNotNull(response);

        assertEquals(ESTUDIO_ID, response.getId());
        assertEquals(NOME_DO_ESTUDIO, response.getNome());
        assertEquals(TELEFONE, response.getTelefone());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(CNPJ, response.getCnpj());

        verify(estudioService, times(1)).cadastrarEstudio(any());
        verify(converter, times(1)).paraDTO(estudio);

    }

    @Test
    void quandoAlteraUmEstudioEntaoRetornaEstudioDTO() {
        when(estudioService.alterarEstudio(ESTUDIO_ID, estudioInput)).thenReturn(estudio);
        when(converter.paraDTO(estudio)).thenReturn(estudioDTO);

        EstudioDTO response = controller.alterarEstudio(ESTUDIO_ID, estudioInput);

        assertEquals(ESTUDIO_ID, response.getId());
        assertEquals(NOME_DO_ESTUDIO, response.getNome());
        assertEquals(TELEFONE, response.getTelefone());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(CNPJ, response.getCnpj());

        verify(estudioService, times(1)).alterarEstudio(ESTUDIO_ID, estudioInput);
        verify(converter, times(1)).paraDTO(estudio);
    }

    @Test
    void quandoRemoverUmEstudio() {
        controller.removerEstudio(ESTUDIO_ID);

        verify(estudioService, times(1)).removerEstudio(ESTUDIO_ID);
    }

    void startEstudio() {
        Estado estado = new Estado(1L, "São Paulo");
        Cidade cidade = new Cidade(1L, "São Paulo", estado);
        Endereco endereco = new Endereco("01234-567", "Rua Principal", "123", "Apto 1", "Centro", cidade);
        estudio = new Estudio(ESTUDIO_ID, NOME_DO_ESTUDIO, TELEFONE, EMAIL, CNPJ, RAZAO_SOCIAL, REDES_SOCIAIS, endereco);
        estudioDTO = new EstudioDTO(ESTUDIO_ID, NOME_DO_ESTUDIO, TELEFONE, EMAIL, CNPJ, true,RAZAO_SOCIAL, REDES_SOCIAIS, endereco);
        estudioInput = new EstudioInput(NOME_DO_ESTUDIO, TELEFONE, EMAIL, CNPJ, RAZAO_SOCIAL, REDES_SOCIAIS, new EnderecoInput());
    }
}