package com.vinicius.biblioteca.dto;

import com.vinicius.biblioteca.enums.Status;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchEmprestimoDTO {

    @NotNull(message = "O novo status é obrigatório (ex: DEVOLVIDO)")
    private Status status;

}