package com.wolftech.sea.mapper;


import com.wolftech.sea.dto.FuncionarioDTO;
import com.wolftech.sea.entity.Funcionario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FuncionarioMapper extends BaseMapper<Funcionario, FuncionarioDTO> {

}
