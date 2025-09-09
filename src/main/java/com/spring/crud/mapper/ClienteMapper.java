package com.spring.crud.mapper;

import com.spring.crud.dto.ClienteDTO;
import com.spring.crud.dto.ClienteRequestDTO;
import com.spring.crud.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDTO toDTO(Cliente cliente);

    Cliente toEntity(ClienteRequestDTO dto);

    void updateEntityFromDto(ClienteRequestDTO dto, @MappingTarget Cliente cliente);

}
