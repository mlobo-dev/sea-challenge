package com.wolftech.sea.enums;

import lombok.Getter;

@Getter
public enum SexoEnum {

    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    OUTROS("Outros");

    private String descricao;

    SexoEnum(String descricao) {
        this.descricao = descricao;
    }
}
