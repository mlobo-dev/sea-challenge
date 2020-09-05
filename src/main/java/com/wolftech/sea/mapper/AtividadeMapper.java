package com.wolftech.sea.mapper;


import com.wolftech.sea.dto.AtividadeDTO;
import com.wolftech.sea.entity.Atividade;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AtividadeMapper extends BaseMapper<Atividade, AtividadeDTO> {

}
