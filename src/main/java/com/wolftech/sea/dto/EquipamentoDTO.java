package com.wolftech.sea.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EquipamentoDTO {

    private Long id;
    @NotNull(message = "Nome do equipamento é obrigatório")
    private String nome;

    @NotNull(message = "Número do Certificado de Aprovação(CA) é obrigatório")
    private Long numeroCA;
}
