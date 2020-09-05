package com.wolftech.sea.enums;

import lombok.Getter;

@Getter
public enum StatusFuncionarioEnum {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private String descricao;
    StatusFuncionarioEnum(String descricao) {
        this.descricao = descricao;
    }
}
