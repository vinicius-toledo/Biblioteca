package com.vinicius.biblioteca.controller;

import com.vinicius.biblioteca.dto.UsuarioDTO;
import com.vinicius.biblioteca.model.Livro;
import com.vinicius.biblioteca.model.Usuario;
import com.vinicius.biblioteca.service.RecomendacaoService;
import com.vinicius.biblioteca.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Operações relacionadas aos usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RecomendacaoService recomendacaoService;

    @Operation(summary = "Cria um novo Usuario")
    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody @Valid UsuarioDTO dto) {

        Usuario novoUsuario = new Usuario();

        novoUsuario.setNome(dto.getNome());
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setTelefone(dto.getTelefone());

        novoUsuario.setDataCadastro(dto.getDataCadastro());

        Usuario usuarioSalvo = usuarioService.salvar(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @Operation(summary = "Lista todos os usuarios")
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @Operation(summary = "Busca um Usuario pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @Operation(summary = "Deleta um Usuario pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        usuarioService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Recomenda livros baseados no histórico do usuário")
    @GetMapping("/{id}/recomendacoes")
    public ResponseEntity<List<Livro>> recomendarLivros(@PathVariable Integer id) {
        List<Livro> recomendacoes = recomendacaoService.recomendarParaUsuario(id);
        return ResponseEntity.ok(recomendacoes);
    }
}