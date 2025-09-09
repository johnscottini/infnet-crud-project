package com.spring.crud.mapper;

import com.spring.crud.dto.FornecedorDTO;
import com.spring.crud.dto.FornecedorRequestDTO;
import com.spring.crud.entity.Fornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {
    FornecedorDTO toDTO(Fornecedor fornecedor);

    Fornecedor toEntity(FornecedorRequestDTO dto);

    void updateEntityFromDto(FornecedorRequestDTO dto, @MappingTarget Fornecedor fornecedor);
}