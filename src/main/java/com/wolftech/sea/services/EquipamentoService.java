package com.wolftech.sea.services;

import com.wolftech.sea.dto.EquipamentoDTO;
import com.wolftech.sea.entity.Equipamento;
import com.wolftech.sea.exception.BusinessRuleException;
import com.wolftech.sea.exception.DataIntegrityException;
import com.wolftech.sea.exception.ObjectNotFoundException;
import com.wolftech.sea.mapper.EquipamentoMapper;
import com.wolftech.sea.repositories.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EquipamentoService {


    private final EquipamentoRepository repository;
    private final EquipamentoMapper mapper;

    @Transactional
    public Equipamento salvar(EquipamentoDTO dto) {
        if (repository.existsByNumeroCA(dto.getNumeroCA())) {
            throw new BusinessRuleException("Não é possível cadastrar um EPI com o mesmo número de Certificado");
        }
        return repository.save(mapper.toEntity(dto));
    }

    @Transactional(readOnly = true)
    public List<Equipamento> listarTudo() {
        return repository.findAll();
    }

    public Equipamento buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Usuário não localizado pelo id: " + id)
        );
    }

    public Equipamento editar(EquipamentoDTO dto) {
        return repository.save(atualizarDados(buscarPorId(dto.getId()), mapper.toEntity(dto)));
    }

    @Transactional
    private Equipamento atualizarDados(Equipamento objSalvo, Equipamento objAtualizacao) {
        objSalvo.setNome(objAtualizacao.getNome() != null ? objAtualizacao.getNome() : objSalvo.getNome());
        objSalvo.setNumeroCA(objAtualizacao.getNumeroCA() != null ? objAtualizacao.getNumeroCA() : objSalvo.getNumeroCA());
        return objSalvo;
    }

    public void deletarEquipamento(Long id) {
        buscarPorId(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não pode ser deletado pois causaria violão na integridade de dados, uma vez que possui relação com outros registros.");
        }
    }

    public Equipamento buscarPeloNumeroCA(Long numeroCA) {
        return repository.findByNumeroCA(
                numeroCA).orElseThrow(() -> new ObjectNotFoundException("Nenhum equipamento localizado pela numero da CA")
        );
    }


}
