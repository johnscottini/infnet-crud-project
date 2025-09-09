package com.spring.crud.mapper;

import com.spring.crud.dto.TransportadoraDTO;
import com.spring.crud.dto.TransportadoraRequestDTO;
import com.spring.crud.entity.Transportadora;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransportadoraMapper {
    TransportadoraDTO toDTO(Transportadora transportadora);

    Transportadora toEntity(TransportadoraRequestDTO dto);

    void updateEntityFromDto(TransportadoraRequestDTO dto, @MappingTarget Transportadora transportadora);
}