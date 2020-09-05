package controller;

import com.wolftech.sea.controller.FuncionarioController;
import com.wolftech.sea.dto.FuncionarioDTO;
import com.wolftech.sea.mapper.FuncionarioMapper;
import com.wolftech.sea.repositories.FuncionarioRepository;
import com.wolftech.sea.services.FuncionarioService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

public class FuncionarioControllerTest {
    @Mock
    FuncionarioService service;

    @Mock
    FuncionarioRepository repository;

    @Mock
    FuncionarioMapper mapper;
    @InjectMocks
    FuncionarioController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnOkOnSave() throws Exception {
        Assert.assertEquals(controller.salvar(criarFuncionarioDTO()).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void shouldReturnOkOnEdit() throws Exception {
        Assert.assertEquals(controller.editar(criarFuncionarioDTO()).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void shouldReturnOkOnFindFuncionarioById() throws Exception {
        Assert.assertEquals(controller.buscarPorId(1L).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReturnOkOnFilter() throws Exception {
        Assert.assertEquals(controller.filtrar(criarFuncionarioDTO()).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReturnOkOnListByStatus() throws Exception {
        Assert.assertEquals(controller.listarPeloStatus("ATIVO").getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReturnOkListFuncionarios() throws Exception {
        Assert.assertEquals(controller.listarTudo().getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReturnNoContentOnDelete() throws Exception {
        Assert.assertEquals(controller.deletar(1L).getStatusCode(), HttpStatus.NO_CONTENT);
    }

    private FuncionarioDTO criarFuncionarioDTO() {
        FuncionarioDTO obj = new FuncionarioDTO();
        obj.setNome("CARGO 1");
        obj.setId(1L);
        return obj;
    }
}
