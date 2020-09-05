package com.wolftech.sea.controller;

import com.wolftech.sea.dto.ArquivoDTO;
import com.wolftech.sea.mapper.ArquivoMapper;
import com.wolftech.sea.services.ArquivoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/arquivos")
@Api(tags = "Arquivos", description = "Rotas para operações com Arquivos")
public class ArquivoController {


    private final ArquivoService service;
    private final ArquivoMapper mapper;

    @GetMapping
    @ApiOperation("Lista todos os Usuários ")
    public ResponseEntity<List<ArquivoDTO>> listarTudo() {
        return ResponseEntity.ok(mapper.toDto(service.listarTudo()));
    }

    @GetMapping("/{id}")
    @ApiOperation("Busca o usuário pelo id")
    public ResponseEntity<ArquivoDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mapper.toDto(service.buscarPorId(id)));
    }

    @PostMapping
    @ApiOperation("Salva um novo usuário ")
    public ResponseEntity<ArquivoDTO> salvar(@RequestBody ArquivoDTO dto) {
        return ResponseEntity.ok(mapper.toDto(service.salvar(dto)));
    }

    @PutMapping
    @ApiOperation("Edita o Arquivo")
    public ResponseEntity<ArquivoDTO> editar(@RequestBody ArquivoDTO dto) {
        return new ResponseEntity(mapper.toDto(service.editar(dto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deleta  o arquivo pelo id")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletarArquivo(id);
        return ResponseEntity.noContent().build();
    }


}
