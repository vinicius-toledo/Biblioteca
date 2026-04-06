package com.vinicius.biblioteca.dto;

import com.vinicius.biblioteca.enums.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmprestimoDTO {

    private Integer id;

    @NotNull(message = "ID do usuário é obrigatório")
    private Integer usuarioId;

    @NotNull(message = "ID do livro é obrigatório")
    private Integer livroId;

    @NotNull(message = "A data de empréstimo é obrigatória")
    @PastOrPresent(message = "A data de empréstimo não pode ser futura")
    private LocalDate dataEmprestimo;

    @NotNull(message = "A data de devolução é obrigatória")
    private LocalDate dataDevolucao;

    private Status status;
}