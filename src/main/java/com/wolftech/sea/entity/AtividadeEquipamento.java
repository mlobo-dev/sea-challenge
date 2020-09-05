package com.wolftech.sea.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_ATIVIDADE_EQUIPAMENTO")
public class AtividadeEquipamento {

    @EmbeddedId
    private AtividadeEquipamentoPK id;

    @ManyToOne
    @JoinColumn(name = "COD_ATIVIDADE", insertable = false, updatable = false)
    private Atividade atividade;

    @ManyToOne
    @JoinColumn(name = "COD_EQUIPAMENTO", insertable = false, updatable = false)
    private Equipamento equipamento;

    @ManyToOne
    @JoinColumn(name = "COD_FUNCIONARIO", insertable = false, updatable = false)
    private Funcionario funcionario;


}
