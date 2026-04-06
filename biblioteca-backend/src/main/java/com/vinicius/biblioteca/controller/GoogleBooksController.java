package com.vinicius.biblioteca.controller;

import com.vinicius.biblioteca.dto.GoogleBooksDTO;
import com.vinicius.biblioteca.model.Livro;
import com.vinicius.biblioteca.service.GoogleBooksService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/google-books")
@Tag(name = "Google Books", description = "Integração com a API do Google Books")
public class GoogleBooksController {

    @Autowired
    private GoogleBooksService googleBooksService;

    @Operation(summary = "Busca livros pelo título na internet")
    @GetMapping("/buscar")
    public ResponseEntity<GoogleBooksDTO> buscar(@RequestParam String titulo) {
        GoogleBooksDTO resposta = googleBooksService.buscarLivroNaGoogle(titulo);
        return ResponseEntity.ok(resposta);
    }

    @Operation(summary = "Busca o primeiro livro no Google e SALVA na nossa biblioteca")
    @PostMapping("/adicionar")
    public ResponseEntity<Livro> adicionarLivroDoGoogle(@RequestParam String titulo) {
        Livro livroSalvo = googleBooksService.salvarLivro(titulo);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
    }
}