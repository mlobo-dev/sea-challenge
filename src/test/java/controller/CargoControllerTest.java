package controller;

import com.wolftech.sea.controller.CargoController;
import com.wolftech.sea.dto.CargoDTO;
import com.wolftech.sea.mapper.CargoMapper;
import com.wolftech.sea.repositories.CargoRepository;
import com.wolftech.sea.services.CargoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

public class CargoControllerTest {
    @Mock
    CargoService service;

    @Mock
    CargoRepository repository;

    @Mock
    CargoMapper mapper;
    @InjectMocks
    CargoController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnOkOnSave() throws Exception {
        Assert.assertEquals(controller.salvar(criarCargoDTO()).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void shouldReturnOkOnEdit() throws Exception {
        Assert.assertEquals(controller.editar(criarCargoDTO()).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void shouldReturnOkOnFindCargoById() throws Exception {
        Assert.assertEquals(controller.buscarPorId(1L).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReturnOkListCargos() throws Exception {
        Assert.assertEquals(controller.listarTudo().getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReturnNoContentOnDelete() throws Exception {
        Assert.assertEquals(controller.deletar(1L).getStatusCode(), HttpStatus.NO_CONTENT);
    }

    private CargoDTO criarCargoDTO() {
        CargoDTO obj = new CargoDTO();
        obj.setNome("CARGO 1");
        obj.setId(1L);
        return obj;
    }
}
