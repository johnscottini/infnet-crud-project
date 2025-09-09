package com.spring.crud.mapper;

import com.spring.crud.dto.ProdutoDTO;
import com.spring.crud.dto.ProdutoRequestDTO;
import com.spring.crud.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    ProdutoDTO toDTO(Produto produto);

    Produto toEntity(ProdutoRequestDTO dto);

    void updateEntityFromDto(ProdutoRequestDTO dto, @MappingTarget Produto produto);

}
