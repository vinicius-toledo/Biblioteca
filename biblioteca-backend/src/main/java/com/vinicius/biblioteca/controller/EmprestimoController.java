package com.vinicius.biblioteca.controller;

import com.vinicius.biblioteca.dto.EmprestimoDTO;
import com.vinicius.biblioteca.dto.PatchEmprestimoDTO;
import com.vinicius.biblioteca.enums.Status;
import com.vinicius.biblioteca.model.Emprestimo;
import com.vinicius.biblioteca.model.Livro;
import com.vinicius.biblioteca.model.Usuario;
import com.vinicius.biblioteca.service.EmprestimoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/emprestimos")
@Tag(name = "Emprestimos" , description = "Operações relacionadas aos empréstimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Operation(summary = "Cria um novo Emprestimo")
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid EmprestimoDTO dto){
        try {
            Livro livro = new Livro();
            livro.setId(dto.getLivroId());

            Usuario usuario = new Usuario();
            usuario.setId(dto.getUsuarioId());

            Emprestimo novoEmprestimo = new Emprestimo();
            novoEmprestimo.setLivro(livro);
            novoEmprestimo.setUsuario(usuario);

            novoEmprestimo.setDataEmprestimo(dto.getDataEmprestimo() != null ? dto.getDataEmprestimo() : LocalDate.now());
            novoEmprestimo.setDataDevolucao(dto.getDataDevolucao());
            novoEmprestimo.setStatus(Status.ATIVO);

            Emprestimo emprestimoSalvo = emprestimoService.salvar(novoEmprestimo);
            return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoSalvo);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @Operation(summary = "Lista todos os empréstimos")
    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarTodos(){
        return ResponseEntity.ok(emprestimoService.listarTodos());
    }

    @Operation(summary = "Busca um Empréstimo pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(emprestimoService.buscarPorId(id));
    }

    @Operation(summary = "Atualiza o status de um empréstimo (Ex: Devolver livro)")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Emprestimo> atualizarStatus(@PathVariable Integer id , @RequestBody @Valid PatchEmprestimoDTO dto){
        Emprestimo emprestimoAtualizado = emprestimoService.atualizarStatus(id, dto.getStatus());
        return ResponseEntity.ok(emprestimoAtualizado);
    }
}