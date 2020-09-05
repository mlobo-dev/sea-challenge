package com.wolftech.sea.services;

import com.wolftech.sea.dto.CargoDTO;
import com.wolftech.sea.entity.Cargo;
import com.wolftech.sea.exception.BusinessRuleException;
import com.wolftech.sea.exception.DataIntegrityException;
import com.wolftech.sea.exception.ObjectNotFoundException;
import com.wolftech.sea.mapper.CargoMapper;
import com.wolftech.sea.repositories.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CargoService {


    private final CargoRepository repository;
    private final CargoMapper mapper;

    @Transactional
    public Cargo salvar(CargoDTO dto) {
        if (dto.getId() == null && repository.existsByNome(dto.getNome())) {
            throw new BusinessRuleException("Já existe um cargo com esse nome.");
        }
        return repository.save(mapper.toEntity(dto));
    }

    @Transactional(readOnly = true)
    public List<Cargo> listarTudo() {
        return repository.findAll();
    }

    public Cargo buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Usuário não localizado pelo id: " + id)
        );
    }

    @Transactional
    public Cargo editar(CargoDTO dto) {
        return repository.save(atualizarDados(buscarPorId(dto.getId()), mapper.toEntity(dto)));
    }

    private Cargo atualizarDados(Cargo objSalvo, Cargo objAtualizacao) {
        objSalvo.setNome(objAtualizacao.getNome() != null ? objAtualizacao.getNome() : objSalvo.getNome());
        return objSalvo;
    }


    public void deletarCargo(Long id) {
        buscarPorId(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não pode ser deletado pois causaria violão na integridade de dados, uma vez que possui relação com outros registros.");
        }
    }

}
