package controller;

import com.wolftech.sea.controller.EquipamentoController;
import com.wolftech.sea.dto.EquipamentoDTO;
import com.wolftech.sea.mapper.EquipamentoMapper;
import com.wolftech.sea.repositories.EquipamentoRepository;
import com.wolftech.sea.services.EquipamentoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

public class EquipamentoControllerTest {
    @Mock
    EquipamentoService service;

    @Mock
    EquipamentoRepository repository;

    @Mock
    EquipamentoMapper mapper;
    @InjectMocks
    EquipamentoController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnOkOnSave() throws Exception {
        Assert.assertEquals(controller.salvar(criarEquipamentoDTO()).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void shouldReturnOkOnEdit() throws Exception {
        Assert.assertEquals(controller.editar(criarEquipamentoDTO()).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void shouldReturnOkOnFindEquipamentoById() throws Exception {
        Assert.assertEquals(controller.buscarPorId(1L).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReturnOkOnFindByNumeroCA() throws Exception {
        Assert.assertEquals(controller.buscarPeloNumeroCA(9999L).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void shouldReturnOkListEquipamentos() throws Exception {
        Assert.assertEquals(controller.listarTudo().getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReturnNoContentOnDelete() throws Exception {
        Assert.assertEquals(controller.deletar(1L).getStatusCode(), HttpStatus.NO_CONTENT);
    }

    private EquipamentoDTO criarEquipamentoDTO() {
        EquipamentoDTO obj = new EquipamentoDTO();
        obj.setNome("CARGO 1");
        obj.setId(1L);
        return obj;
    }
}
