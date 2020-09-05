package com.wolftech.sea.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ArquivoDTO {

    private Long id;
    @NotNull(message = "Base64 do arquivo é obrigatório")
    private Byte[] base64;
}
