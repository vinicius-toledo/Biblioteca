package com.vinicius.biblioteca.controller;

import com.vinicius.biblioteca.dto.LivroDTO;
import com.vinicius.biblioteca.enums.Categoria;
import com.vinicius.biblioteca.model.Livro;
import com.vinicius.biblioteca.service.LivroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/livros")
@Tag(name = "Livros", description = "Operações relacionadas aos livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Operation(summary = "Cria um novo Livro")
    @PostMapping
    public ResponseEntity<Livro> salvar(@RequestBody @Valid LivroDTO dto) {

        Livro novoLivro = new Livro();
        novoLivro.setTitulo(dto.getTitulo());
        novoLivro.setAutor(dto.getAutor());
        novoLivro.setIsbn(dto.getIsbn());
        novoLivro.setDataPublicacao(dto.getDataPublicacao());

        novoLivro.setCategoria(Categoria.valueOf(dto.getCategoria().toUpperCase()));

        Livro livroSalvo = livroService.salvar(novoLivro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
    }

    @Operation(summary = "Lista todos os livros")
    @GetMapping
    public ResponseEntity<List<Livro>> listarTodos() {
        return ResponseEntity.ok(livroService.listarTodos());
    }

    @Operation(summary = "Busca um Livro pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }

    @Operation(summary = "Deleta um Livro pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        livroService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}