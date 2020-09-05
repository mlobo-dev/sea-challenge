package com.wolftech.sea.mapper;


import com.wolftech.sea.dto.CargoDTO;
import com.wolftech.sea.dto.EquipamentoDTO;
import com.wolftech.sea.entity.Cargo;
import com.wolftech.sea.entity.Equipamento;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CargoMapper extends BaseMapper<Cargo, CargoDTO> {

}
