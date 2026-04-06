package com.vinicius.biblioteca.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @NotBlank(message = "O nome é obrigatorio")
    private String nome;

    @NotBlank(message = "O email é obrigarorio")
    @Email(message = "O formato de email é invalido")
    @Column(unique = true)
    private String email;

    @NotNull(message = "A data de cadastro é obrigatorio")
    @PastOrPresent(message = "A data de cadastrro não pode ser maior que o dia atual")
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @NotBlank(message = "O telefone é obrigatorio")
    private String telefone;
}
