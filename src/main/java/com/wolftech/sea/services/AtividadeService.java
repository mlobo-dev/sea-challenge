package com.wolftech.sea.services;

import com.wolftech.sea.dto.AtividadeDTO;
import com.wolftech.sea.dto.AtividadeEquipamentoDTO;
import com.wolftech.sea.dto.EquipamentoDTO;
import com.wolftech.sea.entity.*;
import com.wolftech.sea.exception.BusinessRuleException;
import com.wolftech.sea.exception.DataIntegrityException;
import com.wolftech.sea.exception.ObjectNotFoundException;
import com.wolftech.sea.mapper.AtividadeMapper;
import com.wolftech.sea.mapper.EquipamentoMapper;
import com.wolftech.sea.repositories.AtividadeEquipamentoRepository;
import com.wolftech.sea.repositories.AtividadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AtividadeService {

    private final AtividadeRepository repository;
    private final AtividadeMapper mapper;
    private final EquipamentoMapper equipamentoMapper;
    private final AtividadeEquipamentoRepository atividadeEquipamentoRepository;


    @Transactional
    public Atividade salvar(AtividadeDTO dto) {
        if (dto.getId() == null && repository.existsByNome(dto.getNome())) {
            throw new BusinessRuleException("Já existe um cargo com esse nome.");
        }
        return repository.save(mapper.toEntity(dto));
    }

    @Transactional(readOnly = true)
    public List<Atividade> listarTudo() {
        return repository.findAll();
    }

    public Atividade buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Usuário não localizado pelo id: " + id)
        );
    }

    @Transactional
    public Atividade editar(AtividadeDTO dto) {
        return repository.save(atualizarDados(buscarPorId(dto.getId()), mapper.toEntity(dto)));
    }

    private Atividade atualizarDados(Atividade objSalvo, Atividade objAtualizacao) {
        objSalvo.setNome(objAtualizacao.getNome() != null ? objAtualizacao.getNome() : objSalvo.getNome());
        return objSalvo;
    }

    public void deletarAtividade(Long id) {
        buscarPorId(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não pode ser deletado pois causaria violão na integridade de dados, uma vez que possui relação com outros registros.");
        }
    }


    @Transactional
    public List<AtividadeEquipamentoDTO> salvarRelacaoFuncionarioAtividades(List<AtividadeEquipamentoDTO> atividades, Funcionario funcionario) {

        for (AtividadeEquipamentoDTO dto : atividades) {
            for (EquipamentoDTO equipamentoDTO : dto.getEquipamentos()) {
                AtividadeEquipamentoPK pk = new AtividadeEquipamentoPK();
                pk.setIdEquipamento(equipamentoDTO.getId());
                pk.setIdAtividade(dto.getAtividade().getId());
                pk.setIdFuncionario(funcionario.getId());

                AtividadeEquipamento atividadeEquipamento = new AtividadeEquipamento();
                atividadeEquipamento.setId(pk);
                atividadeEquipamento.setAtividade(mapper.toEntity(dto.getAtividade()));
                atividadeEquipamento.setFuncionario(funcionario);
                atividadeEquipamento.setEquipamento(equipamentoMapper.toEntity(equipamentoDTO));
                atividadeEquipamentoRepository.save(atividadeEquipamento);
            }
        }
        return recuperarAtividadesEEquipamentosDoFuncionario(funcionario.getId());
    }


    public List<AtividadeEquipamentoDTO> recuperarAtividadesEEquipamentosDoFuncionario(Long idFuncionario) {
        List<AtividadeEquipamentoDTO> atividaEquipamentosDTO = new ArrayList<>();
        List<Atividade> atividadesFuncionario = repository.findDistinctInAtividadeEquipamentoByFuncionarioId(idFuncionario);
        for (Atividade atividade : atividadesFuncionario) {
            List<Equipamento> equipamentos = buscarPeloFuncionarioEAtividade(idFuncionario, atividade.getId());
            if (!equipamentos.isEmpty()) {
                AtividadeEquipamentoDTO dto = new AtividadeEquipamentoDTO();
                dto.setAtividade(mapper.toDto(atividade));
                dto.setEquipamentos(equipamentoMapper.toDto(equipamentos));
                if (!atividaEquipamentosDTO.contains(dto)) {
                    atividaEquipamentosDTO.add(dto);
                }
            }
        }
        return atividaEquipamentosDTO;
    }

    public List<Equipamento> buscarPeloFuncionarioEAtividade(Long idFuncionario, Long idAtividade) {
        List<Equipamento> equipamentos = new ArrayList<>();
        List<AtividadeEquipamento> atividadeEquipamentos = atividadeEquipamentoRepository.findAllByFuncionarioIdAndAtividadeId(idFuncionario, idAtividade);
        for (AtividadeEquipamento atividadeEquipamento : atividadeEquipamentos) {
            if (!equipamentos.contains(atividadeEquipamento.getEquipamento())) {
                equipamentos.add(atividadeEquipamento.getEquipamento());
            }
        }
        return equipamentos;
    }

    @Transactional
    public void deletarTudoPeloIdFuncionario(Long idUSuario) {
        atividadeEquipamentoRepository.deleteAllByFuncionarioId(idUSuario);
    }


}
