package com.spring.crud.dto;

import com.spring.crud.entity.enums.Departamento;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncionarioRequestDTO {
    @NotBlank(message = "O nome do funcionário é obrigatório")
    private String nome;
    @NotBlank(message = "O cargo do funcionário é obrigatório")
    private String cargo;
    @Min(value = 0, message = "O salário deve ser maior ou igual a zero")
    @Max(value = 999999, message = "O salário deve ser menor ou igual a 999.999")
    private Double salario;
    private Departamento departamento;
}