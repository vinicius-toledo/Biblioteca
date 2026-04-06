package com.vinicius.biblioteca.service;

import com.vinicius.biblioteca.model.Livro;
import com.vinicius.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro salvar(Livro livro){
        return livroRepository.save(livro);
    }

    public List<Livro> listarTodos(){
       return livroRepository.findAll();
    }

    public Livro buscarPorId(Integer id){
        return livroRepository.findById(id).orElseThrow(()-> new RuntimeException("Livro não encontrado."));
    }

    public void deletar(Integer id) {
        livroRepository.deleteById(id);
    }


}
