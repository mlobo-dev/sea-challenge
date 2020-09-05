package com.wolftech.sea.entity;

import com.wolftech.sea.enums.SexoEnum;
import com.wolftech.sea.enums.StatusEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_CARGO")
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_CARGO")
    private Long id;

    @Column(name = "TX_NOME")
    private String nome;


}
