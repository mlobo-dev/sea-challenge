package com.wolftech.sea.services;

import com.wolftech.sea.dto.ArquivoDTO;
import com.wolftech.sea.entity.Arquivo;
import com.wolftech.sea.exception.DataIntegrityException;
import com.wolftech.sea.exception.ObjectNotFoundException;
import com.wolftech.sea.mapper.ArquivoMapper;
import com.wolftech.sea.repositories.ArquivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArquivoService {

    private final ArquivoRepository repository;

    private final ArquivoMapper mapper;

    @Transactional
    public Arquivo salvar(ArquivoDTO dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Transactional(readOnly = true)
    public List<Arquivo> listarTudo() {
        return repository.findAll();
    }

    public Arquivo buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Usuário não localizado pelo id: " + id)
        );
    }

    public Arquivo editar(ArquivoDTO dto) {
        return repository.save(atualizarDados(buscarPorId(dto.getId()), mapper.toEntity(dto)));
    }


    private Arquivo atualizarDados(Arquivo objSalvo, Arquivo objAtualizacao) {
        objSalvo.setBase64(objAtualizacao.getBase64() != null ? objAtualizacao.getBase64() : objSalvo.getBase64());
        return objSalvo;
    }

    public void deletarArquivo(Long id) {
        buscarPorId(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não pode ser deletado pois causaria violão na integridade de dados, uma vez que possui relação com outros registros.");
        }
    }


}
