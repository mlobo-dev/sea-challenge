package services;

import com.wolftech.sea.dto.*;
import com.wolftech.sea.entity.Atividade;
import com.wolftech.sea.entity.AtividadeEquipamento;
import com.wolftech.sea.entity.Equipamento;
import com.wolftech.sea.entity.Funcionario;
import com.wolftech.sea.enums.SexoEnum;
import com.wolftech.sea.enums.StatusFuncionarioEnum;
import com.wolftech.sea.exception.BusinessRuleException;
import com.wolftech.sea.exception.DataIntegrityException;
import com.wolftech.sea.exception.ObjectNotFoundException;
import com.wolftech.sea.mapper.AtividadeEquipamentoMapper;
import com.wolftech.sea.mapper.AtividadeMapper;
import com.wolftech.sea.mapper.EquipamentoMapper;
import com.wolftech.sea.repositories.AtividadeEquipamentoRepository;
import com.wolftech.sea.repositories.AtividadeRepository;
import com.wolftech.sea.services.AtividadeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class AtividadeServiceTest {

    @InjectMocks
    AtividadeService service;

    @Mock
    AtividadeRepository repository;

    @Mock
    AtividadeEquipamentoMapper atividadeEquipamentoMapper;

    @Mock
    AtividadeEquipamentoRepository atividadeEquipamentoRepository;

    @Mock
    EquipamentoMapper equipamentoMapper;

    @Mock
    AtividadeMapper mapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSave() throws Exception {
        when(repository.save(any(Atividade.class))).thenReturn(new Atividade());
        when(mapper.toEntity(any(AtividadeDTO.class))).thenReturn(new Atividade());
        Atividade result = service.salvar(criarAtividadeDTO());
        Assert.assertTrue(result != null);
    }

    @Test
    public void shouldEdit() throws Exception {
        Atividade cargoAlteracao = criarAtividade();
        cargoAlteracao.setNome("Nome alterado");
        when(repository.save(any(Atividade.class))).thenReturn(cargoAlteracao);
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarAtividade()));
        when(mapper.toEntity(any(AtividadeDTO.class))).thenReturn(new Atividade());
        AtividadeDTO cargoDTO = criarAtividadeDTO();
        cargoDTO.setNome("Nome Alterado");
        Atividade result = service.editar(cargoDTO);
        Assert.assertTrue(result.getNome().equals("Nome alterado"));
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldThroeExcpetionWhenAtividadeExistsByNome() {
        when(repository.existsByNome(anyString())).thenReturn(Boolean.TRUE);
        AtividadeDTO cargo = criarAtividadeDTO();
        cargo.setId(null);
        service.salvar(cargo);
    }

    @Test
    public void shouldReturnAtividadeById() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Atividade()));
        Atividade result = service.buscarPorId(1L);
        Assert.assertTrue(result != null);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldThrowExceptionWhenAtividadeDontExistsById() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Atividade result = service.buscarPorId(1L);
        Assert.assertTrue(result != null);
    }

    @Test
    public void shouldReturnAnListOfAtividade() throws Exception {
        when(repository.findAll()).thenReturn(Arrays.asList(new Atividade()));
        List<Atividade> result = service.listarTudo();
        Assert.assertTrue(result != null);
        Assert.assertTrue(result.size() > 0);

    }

    @Test
    public void shouldDeleteAtividade() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Atividade()));
        service.deletarAtividade(1L);
    }

    @Test(expected = DataIntegrityException.class)
    public void shouldThrowExcpetiononDeleteAtividade() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Atividade()));
        doThrow(DataIntegrityException.class).when(repository).deleteById(anyLong());
        service.deletarAtividade(1L);
    }

    @Test
    public void shouldTest() throws Exception {

        when(repository.findById(anyLong())).thenReturn(Optional.of(criarAtividade()));
        when(mapper.toEntity(any(AtividadeDTO.class))).thenReturn(new Atividade());
        when(atividadeEquipamentoMapper.toDto(any(AtividadeEquipamento.class))).thenReturn(criarAtividadeEquipamentoDTO());
        when(atividadeEquipamentoMapper.toEntity(any(AtividadeEquipamentoDTO.class))).thenReturn(new AtividadeEquipamento());
        when(equipamentoMapper.toEntity(any(EquipamentoDTO.class))).thenReturn(new Equipamento());
        when(atividadeEquipamentoRepository.save(any(AtividadeEquipamento.class))).thenReturn(new AtividadeEquipamento());
        when(repository.findDistinctInAtividadeEquipamentoByFuncionarioId(anyLong())).thenReturn(Arrays.asList(criarAtividade()));
        when(atividadeEquipamentoRepository.findAllByFuncionarioIdAndAtividadeId(anyLong(), anyLong())).thenReturn(Arrays.asList(criarAtividadeEquipamento()));
        AtividadeDTO cargoDTO = criarAtividadeDTO();
        cargoDTO.setNome("Nome Alterado");
        List<AtividadeEquipamentoDTO> result = service.salvarRelacaoFuncionarioAtividades(Arrays.asList(criarAtividadeEquipamentoDTO()), criarFuncionario());
        Assert.assertTrue(result != null);
    }


    private AtividadeDTO criarAtividadeDTO() {
        AtividadeDTO obj = new AtividadeDTO();
        obj.setNome("CARGO DTO");
        obj.setId(1L);
        return obj;
    }

    private AtividadeEquipamentoDTO criarAtividadeEquipamentoDTO() {
        AtividadeEquipamentoDTO obj = new AtividadeEquipamentoDTO();
        obj.setAtividade(criarAtividadeDTO());
        obj.setEquipamentos(Arrays.asList(criarEquipamentoDTO()));
        return obj;
    }

    private AtividadeEquipamento criarAtividadeEquipamento() {
        AtividadeEquipamento obj = new AtividadeEquipamento();
        obj.setAtividade(criarAtividade());
        obj.setEquipamento(criarEquipamento());
        return obj;
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
        obj.setNome("CARGO DTO");
        obj.setNumeroCA(9999L);
        obj.setId(1L);
        return obj;
    }

    private Atividade criarAtividade() {
        Atividade obj = new Atividade();
        obj.setNome("CARGO");
        obj.setId(1L);
        return obj;
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
