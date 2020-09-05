package services;

import com.wolftech.sea.dto.EquipamentoDTO;
import com.wolftech.sea.entity.Equipamento;
import com.wolftech.sea.exception.BusinessRuleException;
import com.wolftech.sea.exception.DataIntegrityException;
import com.wolftech.sea.exception.ObjectNotFoundException;
import com.wolftech.sea.mapper.EquipamentoMapper;
import com.wolftech.sea.repositories.EquipamentoRepository;
import com.wolftech.sea.services.EquipamentoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class EquipamentoServiceTest {

    @InjectMocks
    EquipamentoService service;

    @Mock
    EquipamentoRepository repository;

    @Mock
    EquipamentoMapper mapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSave() throws Exception {
        when(repository.save(any(Equipamento.class))).thenReturn(new Equipamento());
        when(mapper.toEntity(any(EquipamentoDTO.class))).thenReturn(new Equipamento());
        Equipamento result = service.salvar(criarEquipamentoDTO());
        Assert.assertTrue(result != null);
    }

    @Test
    public void shouldEdit() throws Exception {
        Equipamento cargoAlteracao = criarEquipamento();
        cargoAlteracao.setNome("Nome alterado");
        when(repository.save(any(Equipamento.class))).thenReturn(cargoAlteracao);
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarEquipamento()));
        when(mapper.toEntity(any(EquipamentoDTO.class))).thenReturn(new Equipamento());
        EquipamentoDTO cargoDTO = criarEquipamentoDTO();
        cargoDTO.setNome("Nome Alterado");
        Equipamento result = service.editar(cargoDTO);
        Assert.assertTrue(result.getNome().equals("Nome alterado"));
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldThroeExcpetionWhenEquipamentoExistsByNumeroCA() {
        when(repository.existsByNumeroCA(anyLong())).thenReturn(Boolean.TRUE);
        EquipamentoDTO cargo = criarEquipamentoDTO();
        cargo.setId(null);
        service.salvar(cargo);
    }

    @Test
    public void shouldReturnEquipamentoById() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Equipamento()));
        Equipamento result = service.buscarPorId(1L);
        Assert.assertTrue(result != null);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldThrowExceptionWhenEquipamentoDontExistsById() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Equipamento result = service.buscarPorId(1L);
        Assert.assertTrue(result != null);
    }

    @Test
    public void shouldReturnEquipamentoByNumeroCA() throws Exception {
        when(repository.findByNumeroCA(anyLong())).thenReturn(Optional.of(new Equipamento()));
        Equipamento result = service.buscarPeloNumeroCA(1L);
        Assert.assertTrue(result != null);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldThrowExceptionWhenEquipamentoDontExistsByNumeroCA() throws Exception {
        when(repository.findByNumeroCA(anyLong())).thenReturn(Optional.empty());
        Equipamento result = service.buscarPeloNumeroCA(1L);
        Assert.assertTrue(result != null);
    }

    @Test
    public void shouldReturnAnListOfEquipamento() throws Exception {
        when(repository.findAll()).thenReturn(Arrays.asList(new Equipamento()));
        List<Equipamento> result = service.listarTudo();
        Assert.assertTrue(result != null);
        Assert.assertTrue(result.size() > 0);

    }

    @Test
    public void shouldDeleteEquipamento() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Equipamento()));
        service.deletarEquipamento(1L);
    }

    @Test(expected = DataIntegrityException.class)
    public void shouldThrowExcpetiononDeleteEquipamento() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Equipamento()));
        doThrow(DataIntegrityException.class).when(repository).deleteById(anyLong());
        service.deletarEquipamento(1L);
    }


    private EquipamentoDTO criarEquipamentoDTO() {
        EquipamentoDTO obj = new EquipamentoDTO();
        obj.setNome("CARGO DTO");
        obj.setNumeroCA(9999L);
        obj.setId(1L);
        return obj;
    }

    private Equipamento criarEquipamento() {
        Equipamento obj = new Equipamento();
        obj.setNome("CARGO");
        obj.setId(1L);
        return obj;
    }
}
