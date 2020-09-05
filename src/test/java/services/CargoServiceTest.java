package services;

import com.wolftech.sea.dto.CargoDTO;
import com.wolftech.sea.entity.Cargo;
import com.wolftech.sea.exception.BusinessRuleException;
import com.wolftech.sea.exception.DataIntegrityException;
import com.wolftech.sea.exception.ObjectNotFoundException;
import com.wolftech.sea.mapper.CargoMapper;
import com.wolftech.sea.repositories.CargoRepository;
import com.wolftech.sea.services.CargoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class CargoServiceTest {

    @InjectMocks
    CargoService service;

    @Mock
    CargoRepository repository;

    @Mock
    CargoMapper mapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSave() throws Exception {
        when(repository.save(any(Cargo.class))).thenReturn(new Cargo());
        when(mapper.toEntity(any(CargoDTO.class))).thenReturn(new Cargo());
        Cargo result = service.salvar(criarCargoDTO());
        Assert.assertTrue(result != null);
    }

    @Test
    public void shouldEdit() throws Exception {
        Cargo cargoAlteracao = criarCargo();
        cargoAlteracao.setNome("Nome alterado");
        when(repository.save(any(Cargo.class))).thenReturn(cargoAlteracao);
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarCargo()));
        when(mapper.toEntity(any(CargoDTO.class))).thenReturn(new Cargo());
        CargoDTO cargoDTO = criarCargoDTO();
        cargoDTO.setNome("Nome Alterado");
        Cargo result = service.editar(cargoDTO);
        Assert.assertTrue(result.getNome().equals("Nome alterado"));
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldThroeExcpetionWhenCargoExistsByNome() {
        when(repository.existsByNome(anyString())).thenReturn(Boolean.TRUE);
        CargoDTO cargo = criarCargoDTO();
        cargo.setId(null);
        service.salvar(cargo);
    }

    @Test
    public void shouldReturnCargoById() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Cargo()));
        Cargo result = service.buscarPorId(1L);
        Assert.assertTrue(result != null);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldThrowExceptionWhenCargoDontExistsById() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Cargo result = service.buscarPorId(1L);
        Assert.assertTrue(result != null);
    }

    @Test
    public void shouldReturnAnListOfCargo() throws Exception {
        when(repository.findAll()).thenReturn(Arrays.asList(new Cargo()));
        List<Cargo> result = service.listarTudo();
        Assert.assertTrue(result != null);
        Assert.assertTrue(result.size() > 0);

    }

    @Test
    public void shouldDeleteCargo() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Cargo()));
        service.deletarCargo(1L);
    }

    @Test(expected = DataIntegrityException.class)
    public void shouldThrowExcpetiononDeleteCargo() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Cargo()));
        doThrow(DataIntegrityException.class).when(repository).deleteById(anyLong());
        service.deletarCargo(1L);
    }


    private CargoDTO criarCargoDTO() {
        CargoDTO obj = new CargoDTO();
        obj.setNome("CARGO DTO");
        obj.setId(1L);
        return obj;
    }

    private Cargo criarCargo() {
        Cargo obj = new Cargo();
        obj.setNome("CARGO");
        obj.setId(1L);
        return obj;
    }
}
