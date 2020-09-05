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
@Table(name = "TB_FUNCIONARIO")
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_FUNCIONARIO")
    private Long id;

    @Column(name = "TX_NOME")
    private String nome;

    @Column(name = "TX_CPF")
    private String cpf;

    @Column(name = "TX_RG")
    private String rg;

    @Column(name = "TX_SEXO")
    private SexoEnum sexoEnum;

    @Column(name = "DT_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "TX_STATUS")
    private StatusEnum status;

    @Column(name = "BOOL_UTILIZA_EPI")
    private Boolean utilizaEPI;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COD_ARQUIVO")
    private Arquivo atestadoOcupacional;

    @ManyToOne
    @JoinColumn(name = "COD_CARGO")
    private Cargo cargo;


}
