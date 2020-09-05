package com.wolftech.sea.services;

import com.wolftech.sea.dto.AtividadeEquipamentoDTO;
import com.wolftech.sea.dto.FuncionarioDTO;
import com.wolftech.sea.entity.Funcionario;
import com.wolftech.sea.enums.StatusFuncionarioEnum;
import com.wolftech.sea.exception.BusinessRuleException;
import com.wolftech.sea.exception.DataIntegrityException;
import com.wolftech.sea.exception.ObjectNotFoundException;
import com.wolftech.sea.mapper.FuncionarioMapper;
import com.wolftech.sea.repositories.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private final FuncionarioMapper mapper;
    private final AtividadeService atividadeService;

    @Transactional
    public FuncionarioDTO salvar(FuncionarioDTO dto) {
        if (dto.getId() == null && repository.existsByCpf(dto.getCpf())) {
            throw new BusinessRuleException("Este funcionário já existe");
        }
        Funcionario funcionario = repository.save(mapper.toEntity(dto));
        List<AtividadeEquipamentoDTO> atividades = atividadeService.salvarRelacaoFuncionarioAtividades(dto.getAtividades(), funcionario);
        FuncionarioDTO funcionarioSalvo = mapper.toDto(funcionario);
        funcionarioSalvo.setAtividades(atividades);
        return funcionarioSalvo;
    }

    @Transactional(readOnly = true)
    public List<Funcionario> listarTudo() {
        return repository.findAll();
    }

    public List<Funcionario> filtrar(FuncionarioDTO dto) {
        Funcionario funcionario = mapper.toEntity(dto);

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<Funcionario> example = Example.of(funcionario, matcher);
        return repository.findAll(example);

    }

    @Transactional(readOnly = true)
    public List<Funcionario> listarTudoPeloStatus(String status) {

        try {
            return repository.findAllByStatus(StatusFuncionarioEnum.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new ObjectNotFoundException("O status informado " + status + " não existe");
        }

    }


    public Funcionario buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Usuário não localizado pelo id: " + id)
        );
    }

    public FuncionarioDTO buscarComAtividadesPeloId(Long id) {
        FuncionarioDTO dto = mapper.toDto(buscarPorId(id));
        dto.setAtividades(atividadeService.recuperarAtividadesEEquipamentosDoFuncionario(id));
        return dto;
    }

    @Transactional
    public FuncionarioDTO editar(FuncionarioDTO dto) {
        return salvar(atualizarDados(mapper.toDto(buscarPorId(dto.getId())), dto));
    }

    private FuncionarioDTO atualizarDados(FuncionarioDTO objSalvo, FuncionarioDTO objAtualizacao) {
        objSalvo.setAtestadoOcupacional(objAtualizacao.getAtestadoOcupacional() != null ? objAtualizacao.getAtestadoOcupacional() : objSalvo.getAtestadoOcupacional());
        objSalvo.setCpf(objAtualizacao.getCpf() != null ? objAtualizacao.getCpf() : objSalvo.getCpf());
        objSalvo.setNome(objAtualizacao.getNome() != null ? objAtualizacao.getNome() : objSalvo.getNome());
        objSalvo.setRg(objAtualizacao.getRg() != null ? objAtualizacao.getRg() : objSalvo.getRg());
        objSalvo.setDataNascimento(objAtualizacao.getDataNascimento() != null ? objAtualizacao.getDataNascimento() : objSalvo.getDataNascimento());
        objSalvo.setSexoEnum(objAtualizacao.getSexoEnum() != null ? objAtualizacao.getSexoEnum() : objSalvo.getSexoEnum());
        objSalvo.setStatus(objAtualizacao.getStatus() != null ? objAtualizacao.getStatus() : objSalvo.getStatus());
        objSalvo.setCargo(objAtualizacao.getCargo() != null ? objAtualizacao.getCargo() : objSalvo.getCargo());
        objSalvo.setUtilizaEPI(objAtualizacao.getUtilizaEPI() != null ? objAtualizacao.getUtilizaEPI() : objSalvo.getUtilizaEPI());
        return objSalvo;
    }

    @Transactional
    public void deletarFuncionario(Long id) {
        buscarPorId(id);
        try {
            atividadeService.deletarTudoPeloIdFuncionario(id);
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não pode ser deletado pois causaria violão na integridade de dados, uma vez que possui relação com outros registros.");
        }
    }


}
