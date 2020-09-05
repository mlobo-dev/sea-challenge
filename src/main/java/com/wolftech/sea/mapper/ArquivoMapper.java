package com.wolftech.sea.mapper;


import com.wolftech.sea.dto.ArquivoDTO;
import com.wolftech.sea.dto.FuncionarioDTO;
import com.wolftech.sea.entity.Arquivo;
import com.wolftech.sea.entity.Funcionario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArquivoMapper extends BaseMapper<Arquivo, ArquivoDTO> {

}
