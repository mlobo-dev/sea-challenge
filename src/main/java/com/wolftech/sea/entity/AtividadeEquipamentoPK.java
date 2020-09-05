package com.wolftech.sea.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AtividadeEquipamentoPK implements Serializable {

    @Column(name = "COD_ATIVIDADE")
    private Long idAtividade;

    @Column(name = "COD_EQUIPAMENTO")
    private Long idEquipamento;

    @Column(name = "COD_FUNCIONARIO")
    private Long idFuncionario;

}
