package com.vinicius.biblioteca.service;

import com.vinicius.biblioteca.model.Usuario;
import com.vinicius.biblioteca.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve salvar um usuário com sucesso")
    void deveSalvarUsuarioComSucesso() {

        Usuario usuario = new Usuario();
        usuario.setNome("Vinicius");
        usuario.setEmail("vinicius@email.com");
        usuario.setDataCadastro(LocalDate.now());

        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = usuarioService.salvar(usuario);

        assertNotNull(resultado);
        assertEquals("Vinicius", resultado.getNome());
        Mockito.verify(usuarioRepository, Mockito.times(1)).save(usuario);
    }

    @Test
    @DisplayName("Deve buscar um usuário por ID com sucesso")
    void deveBuscarUsuarioPorIdComSucesso() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Maria");

        Mockito.when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("Maria", resultado.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção quando o usuário não existir")
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        Mockito.when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.buscarPorId(99);
        });
        assertEquals("Usuario não encontrado!", exception.getMessage());
    }
}