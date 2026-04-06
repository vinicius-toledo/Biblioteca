package com.vinicius.biblioteca.repository;

import com.vinicius.biblioteca.enums.Status;
import com.vinicius.biblioteca.model.Emprestimo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo , Integer> {

    boolean existsByLivroIdAndStatus(Integer livroId, Status status);;

    List<Emprestimo> findByUsuarioId(Integer usuarioId);

}
