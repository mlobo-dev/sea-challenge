package services;

import com.wolftech.sea.dto.ArquivoDTO;
import com.wolftech.sea.dto.CargoDTO;
import com.wolftech.sea.dto.EquipamentoDTO;
import com.wolftech.sea.dto.FuncionarioDTO;
import com.wolftech.sea.entity.Funcionario;
import com.wolftech.sea.enums.SexoEnum;
import com.wolftech.sea.enums.StatusFuncionarioEnum;
import com.wolftech.sea.exception.BusinessRuleException;
import com.wolftech.sea.exception.DataIntegrityException;
import com.wolftech.sea.exception.ObjectNotFoundException;
import com.wolftech.sea.mapper.FuncionarioMapper;
import com.wolftech.sea.repositories.FuncionarioRepository;
import com.wolftech.sea.services.AtividadeService;
import com.wolftech.sea.services.FuncionarioService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class FuncionarioServiceTest {

    @InjectMocks
    FuncionarioService service;

    @Mock
    FuncionarioRepository repository;

    @Mock
    FuncionarioMapper mapper;

    @Mock
    AtividadeService atividadeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSave() throws Exception {
        when(repository.save(any(Funcionario.class))).thenReturn(new Funcionario());
        when(mapper.toEntity(any(FuncionarioDTO.class))).thenReturn(new Funcionario());
        when(atividadeService.salvarRelacaoFuncionarioAtividades(anyList(), any(Funcionario.class))).thenReturn(new ArrayList<>());
        when(mapper.toDto(any(Funcionario.class))).thenReturn(new FuncionarioDTO());
        FuncionarioDTO result = service.salvar(criarFuncionarioDTO());
        Assert.assertTrue(result != null);


    }

    @Test(expected = BusinessRuleException.class)
    public void shouldThroeExcpetionWhenFuncionarioExistsPeloCpf() {
        when(repository.existsByCpf(anyString())).thenReturn(Boolean.TRUE);
        FuncionarioDTO funcionario = criarFuncionarioDTO();
        funcionario.setId(null);
        funcionario.setCpf("12345678911");
        service.salvar(funcionario);
    }

    @Test
    public void shouldEdit() throws Exception {
        Funcionario funcionarioAlteracao = criarFuncionario();
        funcionarioAlteracao.setNome("Nome alterado");
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Funcionario()));
        when(repository.save(any(Funcionario.class))).thenReturn(new Funcionario());
        when(atividadeService.salvarRelacaoFuncionarioAtividades(anyList(), any(Funcionario.class))).thenReturn(new ArrayList<>());
        when(mapper.toDto(any(Funcionario.class))).thenReturn(new FuncionarioDTO());
        when(mapper.toEntity(any(FuncionarioDTO.class))).thenReturn(new Funcionario());
        FuncionarioDTO funcionarioDTO = criarFuncionarioDTO();
        funcionarioDTO.setNome("Nome Alterado");
        FuncionarioDTO result = service.editar(funcionarioDTO);
        Assert.assertTrue(result.getNome().equals("Nome Alterado"));
    }


    @Test
    public void shouldReturnFuncionarioById() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Funcionario()));
        Funcionario result = service.buscarPorId(1L);
        Assert.assertTrue(result != null);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldThrowExceptionWhenFuncionarioDontExistsById() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Funcionario result = service.buscarPorId(1L);
        Assert.assertTrue(result != null);
    }

    @Test
    public void shouldReturnFuncionarioWithAtividadesById() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Funcionario()));
        when(atividadeService.salvarRelacaoFuncionarioAtividades(anyList(), any(Funcionario.class))).thenReturn(new ArrayList<>());
        when(mapper.toDto(any(Funcionario.class))).thenReturn(new FuncionarioDTO());
        when(mapper.toEntity(any(FuncionarioDTO.class))).thenReturn(new Funcionario());

        FuncionarioDTO result = service.buscarComAtividadesPeloId(1L);
        Assert.assertTrue(result != null);
    }


    @Test
    public void shouldReturnAnListOfFuncionario() throws Exception {
        when(repository.findAll()).thenReturn(Arrays.asList(new Funcionario()));
        List<Funcionario> result = service.listarTudo();
        Assert.assertTrue(result != null);
        Assert.assertTrue(result.size() > 0);

    }

    @Test
    public void shouldReturnAnListOfFuncionarioByStatus() throws Exception {
        when(repository.findAllByStatus(any(StatusFuncionarioEnum.class))).thenReturn(Arrays.asList(new Funcionario()));
        List<Funcionario> result = service.listarTudoPeloStatus("ATIVO");
        Assert.assertTrue(result != null);
        Assert.assertTrue(result.size() > 0);

    }

    @Test
    public void shouldDeleteFuncionario() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Funcionario()));
        service.deletarFuncionario(1L);
    }


    @Test(expected = DataIntegrityException.class)
    public void shouldThrowExcpetiononDeleteFuncionario() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Funcionario()));
        doThrow(DataIntegrityException.class).when(repository).deleteById(anyLong());
        service.deletarFuncionario(1L);
    }


    private FuncionarioDTO criarFuncionarioDTO() {
        FuncionarioDTO obj = new FuncionarioDTO();
        obj.setNome("CARGO DTO");
        obj.setId(1L);
        obj.setAtividades(new ArrayList<>());
        obj.setCpf("12345678911");
        obj.setAtestadoOcupacional(new ArquivoDTO());
        obj.setCargo(new CargoDTO());
        obj.setRg("1234567");
        obj.setDataNascimento(LocalDate.now());
        obj.setSexoEnum(SexoEnum.FEMININO);
        obj.setStatus(StatusFuncionarioEnum.ATIVO);
        return obj;
    }

    private Funcionario criarFuncionario() {
        Funcionario obj = new Funcionario();
        obj.setNome("CARGO DTO");
        obj.setId(1L);
        obj.setCpf("12345678911");
        obj.setRg("1234567");
        obj.setDataNascimento(LocalDate.now());
        obj.setSexoEnum(SexoEnum.FEMININO);
        obj.setStatus(StatusFuncionarioEnum.ATIVO);
        return obj;
    }


}
