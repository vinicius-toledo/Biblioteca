package com.vinicius.biblioteca.model;

import com.vinicius.biblioteca.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @NotNull(message = "O usuário é obrigatório")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    @NotNull(message = "O livro é obrigatório")
    private Livro livro;

    @NotNull(message = "A data de empréstimo é obrigatória")
    @PastOrPresent(message = "A data de empréstimo não pode ser no futuro")
    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucao;

    @NotNull(message = "O status é obrigatório")
    @Enumerated(EnumType.STRING)
    private Status status;
}