package com.wolftech.sea.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_EQUIPAMENTO")
public class Equipamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_EQUIPAMENTO")
    private Long id;

    @Column(name = "TX_NOME")
    private String nome;

    @Column(name = "NU_CERTIFICADO_APROVACAO")
    private Long numeroCA;


}
