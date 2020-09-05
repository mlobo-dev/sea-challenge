package controller;

import com.wolftech.sea.controller.AtividadeController;
import com.wolftech.sea.dto.AtividadeDTO;
import com.wolftech.sea.mapper.AtividadeMapper;
import com.wolftech.sea.repositories.AtividadeRepository;
import com.wolftech.sea.services.AtividadeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

public class AtividadeControllerTest {
    @Mock
    AtividadeService service;

    @Mock
    AtividadeRepository repository;

    @Mock
    AtividadeMapper mapper;
    @InjectMocks
    AtividadeController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnOkOnSave() throws Exception {
        Assert.assertEquals(controller.salvar(criarAtividadeDTO()).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void shouldReturnOkOnEdit() throws Exception {
        Assert.assertEquals(controller.editar(criarAtividadeDTO()).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void shouldReturnOkOnFindAtividadeById() throws Exception {
        Assert.assertEquals(controller.buscarPorId(1L).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReturnOkListAtividades() throws Exception {
        Assert.assertEquals(controller.listarTudo().getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReturnNoContentOnDelete() throws Exception {
        Assert.assertEquals(controller.deletar(1L).getStatusCode(), HttpStatus.NO_CONTENT);
    }

    private AtividadeDTO criarAtividadeDTO() {
        AtividadeDTO obj = new AtividadeDTO();
        obj.setNome("CARGO 1");
        obj.setId(1L);
        return obj;
    }
}
