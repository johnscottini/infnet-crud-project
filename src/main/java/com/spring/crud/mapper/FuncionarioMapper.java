package com.spring.crud.mapper;

import com.spring.crud.dto.FuncionarioDTO;
import com.spring.crud.dto.FuncionarioRequestDTO;
import com.spring.crud.entity.Funcionario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

    FuncionarioDTO toDTO(Funcionario funcionario);

    Funcionario toEntity(FuncionarioRequestDTO dto);

    void updateEntityFromDto(FuncionarioRequestDTO dto, @MappingTarget Funcionario funcionario);
}
