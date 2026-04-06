package com.vinicius.biblioteca.repository;

import com.vinicius.biblioteca.enums.Categoria;
import com.vinicius.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    List<Livro> findByCategoriaInAndIdNotIn(Set<Categoria> categorias, Set<Integer> ids);
}