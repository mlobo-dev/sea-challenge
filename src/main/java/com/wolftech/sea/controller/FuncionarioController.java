package com.wolftech.sea.controller;

import com.wolftech.sea.dto.FuncionarioDTO;
import com.wolftech.sea.mapper.FuncionarioMapper;
import com.wolftech.sea.services.FuncionarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/funcionarios")
@Api(tags = "Funcionarios", description = "Rotas Sobre Funcionários")
public class FuncionarioController {


    private final FuncionarioService service;
    private final FuncionarioMapper mapper;

    @GetMapping
    @ApiOperation("Lista todos os Funcionarios ")
    public ResponseEntity<List<FuncionarioDTO>> listarTudo() {
        return ResponseEntity.ok(mapper.toDto(service.listarTudo()));
    }

    @GetMapping("/status/{status}")
    @ApiOperation("Lista todos os Funcionarios pelo Status ")
    public ResponseEntity<List<FuncionarioDTO>> listarPeloStatus(@PathVariable("status") String status) {
        return ResponseEntity.ok(mapper.toDto(service.listarTudoPeloStatus(status)));
    }

    @PostMapping("/filtrar")
    @ApiOperation("Retorna uma Lista de funcionários com base nos atributos no corpo da requisição.")
    public ResponseEntity<List<FuncionarioDTO>> filtrar(@RequestBody FuncionarioDTO dto) {
        return ResponseEntity.ok(mapper.toDto(service.filtrar(dto)));
    }

    @GetMapping("/{id}")
    @ApiOperation("Busca o funcionário pelo id")
    public ResponseEntity<FuncionarioDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.buscarComAtividadesPeloId(id));
    }

    @PostMapping
    @ApiOperation("Salva um novo funcionário ")
    public ResponseEntity<FuncionarioDTO> salvar(@Validated @RequestBody FuncionarioDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @PutMapping
    @ApiOperation("Edita o funcionário")
    public ResponseEntity<FuncionarioDTO> editar(@RequestBody FuncionarioDTO dto) {
        return new ResponseEntity(service.editar(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deleta  o funcionário pelo id")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }


}
