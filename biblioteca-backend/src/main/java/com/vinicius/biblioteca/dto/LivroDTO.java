package com.vinicius.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {

    private Integer id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Autor é obrigatório")
    private String autor;

    @NotBlank(message = "ISBN é obrigatório")
    private String isbn;

    @NotNull(message = "Data de publicação é obrigatória")
    private LocalDate dataPublicacao;

    @NotBlank(message = "Categoria é obrigatória")
    private String categoria;
}