package com.vinicius.biblioteca.service;


import com.vinicius.biblioteca.model.Usuario;
import com.vinicius.biblioteca.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        return  usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Integer id){
        return usuarioRepository.findById(id).orElseThrow(()-> new RuntimeException("Usuario não encontrado!"));
    }

    public void deletar(Integer id){
        usuarioRepository.deleteById(id);
    }
}
