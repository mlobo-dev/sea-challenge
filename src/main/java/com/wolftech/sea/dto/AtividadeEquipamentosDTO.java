package com.wolftech.sea.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AtividadeEquipamentosDTO {

    private AtividadeDTO atividade;
    private List<EquipamentoDTO> equipamentos = new ArrayList<>();
}
