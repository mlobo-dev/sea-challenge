package com.wolftech.sea.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtividadeDTO {

    private Long id;

    @NotEmpty(message = "O nome é obrigatorio!")
    private String nome;

}
