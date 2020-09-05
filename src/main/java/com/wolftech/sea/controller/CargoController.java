package com.wolftech.sea.controller;

import com.wolftech.sea.dto.CargoDTO;
import com.wolftech.sea.mapper.CargoMapper;
import com.wolftech.sea.services.CargoService;
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
@RequestMapping("/cargos")
@Api(tags = "Cargos", description = "Rotas para operações sobre Cargos")
public class CargoController {


    private final CargoService service;
    private final CargoMapper mapper;

    @GetMapping
    @ApiOperation("Lista todos os Cargos ")
    public ResponseEntity<List<CargoDTO>> listarTudo() {
        return ResponseEntity.ok(mapper.toDto(service.listarTudo()));
    }

    @GetMapping("/{id}")
    @ApiOperation("Busca o cargo pelo id")
    public ResponseEntity<CargoDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mapper.toDto(service.buscarPorId(id)));
    }

    @PostMapping
    @ApiOperation("Salva um novo Cargo ")
    public ResponseEntity<CargoDTO> salvar(@Validated @RequestBody CargoDTO dto) {
        return ResponseEntity.ok(mapper.toDto(service.salvar(dto)));
    }

    @PutMapping
    @ApiOperation("Edita o Cargo")
    public ResponseEntity<CargoDTO> editar(CargoDTO dto) {
        return new ResponseEntity(service.editar(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deleta  o cargo pelo id")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletarCargo(id);
        return ResponseEntity.noContent().build();
    }


}
