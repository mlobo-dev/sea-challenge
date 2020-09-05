package com.wolftech.sea.controller;

import com.wolftech.sea.dto.AtividadeDTO;
import com.wolftech.sea.mapper.AtividadeMapper;
import com.wolftech.sea.services.AtividadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/atividades")
@Api(tags = "Atividades", description = "Rotas para operações com Atividades")
public class AtividadeController {


    private final AtividadeService service;
    private final AtividadeMapper mapper;

    @GetMapping
    @ApiOperation("Lista todos os Usuários ")
    public ResponseEntity<List<AtividadeDTO>> listarTudo() {
        return ResponseEntity.ok(mapper.toDto(service.listarTudo()));
    }

    @GetMapping("/{id}")
    @ApiOperation("Busca o usuário pelo id")
    public ResponseEntity<AtividadeDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mapper.toDto(service.buscarPorId(id)));
    }

    @PostMapping
    @ApiOperation("Salva um novo usuário ")
    public ResponseEntity<AtividadeDTO> salvar(@RequestBody AtividadeDTO dto) {
        return ResponseEntity.ok(mapper.toDto(service.salvar(dto)));
    }

    @PutMapping
    @ApiOperation("Edita o usuário")
    public ResponseEntity<AtividadeDTO> editar(@RequestBody AtividadeDTO dto) {
        return new ResponseEntity(mapper.toDto(service.editar(dto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deleta  o usuário pelo id")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletarAtividade(id);
        return ResponseEntity.noContent().build();
    }


}
