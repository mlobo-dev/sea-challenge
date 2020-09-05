package com.wolftech.sea.controller;

import com.wolftech.sea.dto.EquipamentoDTO;
import com.wolftech.sea.mapper.EquipamentoMapper;
import com.wolftech.sea.services.EquipamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/equipamentos")
@Api(tags = "Equipamentos", description = "Rotas para operações com Equipamentos")
public class EquipamentoController {


    private final EquipamentoService service;
    private final EquipamentoMapper mapper;


    @PostMapping
    @ApiOperation("Salva um novo usuário ")
    public ResponseEntity<EquipamentoDTO> salvar(@RequestBody EquipamentoDTO dto) {
        return ResponseEntity.ok(mapper.toDto(service.salvar(dto)));
    }

    @GetMapping
    @ApiOperation("Lista todos os Usuários ")
    public ResponseEntity<List<EquipamentoDTO>> listarTudo() {
        return ResponseEntity.ok(mapper.toDto(service.listarTudo()));
    }

    @GetMapping("/{id}")
    @ApiOperation("Busca o usuário pelo id")
    public ResponseEntity<EquipamentoDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mapper.toDto(service.buscarPorId(id)));
    }

    @GetMapping("/ca/{ca}")
    @ApiOperation("Lista todos os Usuários ")
    public ResponseEntity<EquipamentoDTO> buscarPeloNumeroCA(@PathVariable("ca") Long ca) {
        return ResponseEntity.ok(mapper.toDto(service.buscarPeloNumeroCA(ca)));
    }


    @PutMapping
    @ApiOperation("Edita o usuário")
    public ResponseEntity<EquipamentoDTO> editar(@RequestBody EquipamentoDTO dto) {
        return new ResponseEntity(mapper.toDto(service.editar(dto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deleta  o usuário pelo id")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletarEquipamento(id);
        return ResponseEntity.noContent().build();
    }


}
