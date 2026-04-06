package com.vinicius.biblioteca.service;

import com.vinicius.biblioteca.enums.Categoria;
import com.vinicius.biblioteca.model.Emprestimo;
import com.vinicius.biblioteca.model.Livro;
import com.vinicius.biblioteca.repository.EmprestimoRepository;
import com.vinicius.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecomendacaoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> recomendarParaUsuario(Integer usuarioId) {

        List<Emprestimo> historico = emprestimoRepository.findByUsuarioId(usuarioId);

        if (historico.isEmpty()) {
            return List.of();
        }

        Set<Integer> livrosLidosIds = historico.stream().map(emprestimo -> emprestimo.getLivro().getId()).collect(Collectors.toSet());

        Set<Categoria> categoriasFavoritas = historico.stream().map(emprestimo -> emprestimo.getLivro().getCategoria()).collect(Collectors.toSet());

        return livroRepository.findByCategoriaInAndIdNotIn(categoriasFavoritas, livrosLidosIds);
    }
}