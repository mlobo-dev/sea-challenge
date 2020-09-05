package com.wolftech.sea.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private String descricao;
    StatusEnum(String descricao) {
        this.descricao = descricao;
    }
}
