package com.vinicius.biblioteca.service;

import com.vinicius.biblioteca.enums.Status;
import com.vinicius.biblioteca.model.Emprestimo;
import com.vinicius.biblioteca.repository.EmprestimoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public Emprestimo salvar(Emprestimo emprestimo){
        boolean jaEstaEmprestado = emprestimoRepository.existsByLivroIdAndStatus(emprestimo.getLivro().getId(), Status.ATIVO);

        if (jaEstaEmprestado){
            throw new RuntimeException("Não sera possivel realizar o emprestimo. Esse livro ja está ATIVO em outro emprestimo.");
        }

        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listarTodos(){
        return emprestimoRepository.findAll();
    }

    public Emprestimo buscarPorId(Integer id){
        return emprestimoRepository.findById(id).orElseThrow(()-> new RuntimeException("Emprestimo não encontrado."));
    }

    public Emprestimo atualizarStatus(Integer id , Status novoStatus){
        Emprestimo emprestimo = buscarPorId(id);

        emprestimo.setStatus(novoStatus);

        if(Status.DEVOLVIDO.equals(novoStatus)){
            emprestimo.setDataDevolucao(LocalDate.now());
        }

        return emprestimoRepository.save(emprestimo);
    }
}