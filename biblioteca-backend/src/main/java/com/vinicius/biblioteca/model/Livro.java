package com.vinicius.biblioteca.model;

import com.vinicius.biblioteca.enums.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O titulo é obrigatorio")
    private String titulo;

    @NotBlank(message = "O autor é obrigatorio")
    private String autor;

    @NotBlank(message = "O ISBN é obrigatorio")
    private String isbn;

    @NotNull(message = "A data de publicação é obrigatoria")
    private LocalDate dataPublicacao;

    @NotNull(message = "A categoria é obrigatoria")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}