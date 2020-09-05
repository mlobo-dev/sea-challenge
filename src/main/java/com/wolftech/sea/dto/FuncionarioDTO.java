package com.wolftech.sea.dto;

import com.wolftech.sea.enums.SexoEnum;
import com.wolftech.sea.enums.StatusFuncionarioEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FuncionarioDTO {


    private Long id;
    @NotNull(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "CPF é obrigatório")
    @CPF(message = "Informe um CPF válido")
    private String cpf;

    @NotNull(message = "RG é obrigatório")
    @Size(max = 7, min = 7)
    private String rg;

    @NotNull(message = "Sexo é obrigatório")
    private SexoEnum sexoEnum;

    @NotNull(message = "Data de nascimento é obrigatória e  deve ser informada no padrão iso 8601 yyyy-mm-dd")
    private LocalDate dataNascimento;

    @NotNull(message = "Sexo é obrigatório")
    private StatusFuncionarioEnum status;

    private Boolean utilizaEPI;

    private ArquivoDTO atestadoOcupacional;

    private CargoDTO cargo;

    private List<AtividadeEquipamentoDTO> atividades = new ArrayList<>();
}
