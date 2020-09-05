package com.wolftech.sea.dto;

import com.wolftech.sea.enums.SexoEnum;
import com.wolftech.sea.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FuncionarioDTO {


    private String nome;
    private String cpf;
    private String rg;
    private SexoEnum sexoEnum;
    private LocalDate dataNascimento;
    private StatusEnum status;
    private Boolean utilizaEPI;
    private ArquivoDTO atestadoOcupacional;
    private List<AtividadeEquipamentosDTO> atividades = new ArrayList<>();
}
