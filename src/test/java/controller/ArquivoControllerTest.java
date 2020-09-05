package controller;

import com.wolftech.sea.controller.ArquivoController;
import com.wolftech.sea.dto.ArquivoDTO;
import com.wolftech.sea.mapper.ArquivoMapper;
import com.wolftech.sea.repositories.ArquivoRepository;
import com.wolftech.sea.services.ArquivoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

public class ArquivoControllerTest {
    @Mock
    ArquivoService service;

    @Mock
    ArquivoRepository repository;

    @Mock
    ArquivoMapper mapper;
    @InjectMocks
    ArquivoController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnOkOnSave() throws Exception {
        Assert.assertEquals(controller.salvar(criarArquivoDTO()).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void shouldReturnOkOnEdit() throws Exception {
        Assert.assertEquals(controller.editar(criarArquivoDTO()).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void shouldReturnOkOnFindArquivoById() throws Exception {
        Assert.assertEquals(controller.buscarPorId(1L).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void shouldReturnOkListArquivos() throws Exception {
        Assert.assertEquals(controller.listarTudo().getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReturnNoContentOnDelete() throws Exception {
        Assert.assertEquals(controller.deletar(1L).getStatusCode(), HttpStatus.NO_CONTENT);
    }

    private ArquivoDTO criarArquivoDTO() {
        ArquivoDTO obj = new ArquivoDTO();
        obj.setBase64(new byte[999]);
        obj.setId(1L);
        return obj;
    }
}
