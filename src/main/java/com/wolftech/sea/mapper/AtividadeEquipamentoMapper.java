package com.wolftech.sea.mapper;


import com.wolftech.sea.dto.AtividadeDTO;
import com.wolftech.sea.dto.AtividadeEquipamentoDTO;
import com.wolftech.sea.entity.Atividade;
import com.wolftech.sea.entity.AtividadeEquipamento;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AtividadeEquipamentoMapper extends BaseMapper<AtividadeEquipamento, AtividadeEquipamentoDTO> {

}
