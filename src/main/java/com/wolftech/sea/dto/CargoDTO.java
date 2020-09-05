package com.wolftech.sea.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CargoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nome;


}
