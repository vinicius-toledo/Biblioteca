import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BibliotecaService } from '../../services/biblioteca.service';
import { Usuario, Livro, Emprestimo } from '../../models/biblioteca.models';

@Component({
  selector: 'app-emprestimo-gestao',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './emprestimo-gestao.html',
  styleUrl: './emprestimo-gestao.css'
})
export class EmprestimoGestaoComponent implements OnInit {
  
  usuarios: Usuario[] = [];
  livros: Livro[] = [];
  emprestimosRegistrados: any[] = []; 
  recomendacoes: Livro[] = []; 

  novoEmprestimo = {
    usuario: { id: null },
    livro: { id: null },
    dataEmprestimo: '',
    dataDevolucao: '',
    status: 'ATIVO'
  };

  constructor(private bibliotecaService: BibliotecaService) {}

  ngOnInit(): void {
    this.carregarDados();
  }

  carregarDados() {
    this.bibliotecaService.listarUsuarios().subscribe(data => this.usuarios = data);
    this.bibliotecaService.listarLivros().subscribe(data => this.livros = data);
    this.atualizarListaEmprestimos();
  }

  atualizarListaEmprestimos() {
    this.bibliotecaService.listarEmprestimos().subscribe(data => {
      this.emprestimosRegistrados = data;
    });
  }

  buscarRecomendacoes() {
    const id = this.novoEmprestimo.usuario.id;
    if (id) {
      this.bibliotecaService.getRecomendacoes(id).subscribe({
        next: (res) => this.recomendacoes = res,
        error: () => this.recomendacoes = []
      });
    } else {
      this.recomendacoes = [];
    }
  }

  devolverLivro(idEmprestimo: number) {
    if (confirm("Confirmar a devolução deste livro?")) {
      this.bibliotecaService.atualizarStatusEmprestimo(idEmprestimo, 'DEVOLVIDO').subscribe({
        next: () => {
          alert('Livro devolvido com sucesso!');
          this.atualizarListaEmprestimos(); 
        },
        error: (err) => alert('Erro ao devolver o livro.')
      });
    }
  }

  finalizarEmprestimo() {
    if (!this.novoEmprestimo.usuario.id || !this.novoEmprestimo.livro.id) {
      alert('Por favor, selecione um usuário e um livro!');
      return;
    }

    const dadosParaEnviar = {
      usuarioId: Number(this.novoEmprestimo.usuario.id),
      livroId: Number(this.novoEmprestimo.livro.id),
      dataEmprestimo: this.novoEmprestimo.dataEmprestimo,
      dataDevolucao: this.novoEmprestimo.dataDevolucao,
      status: 'ATIVO'
    };

    this.bibliotecaService.salvarEmprestimo(dadosParaEnviar as any).subscribe({
      next: (res) => {
        alert('Empréstimo realizado com sucesso!');
        this.resetarForm();
        this.atualizarListaEmprestimos(); 
      },
      error: (err) => {
        const msg = err.error?.message || 'Erro ao realizar empréstimo.';
        alert(msg);
      }
    });
  }

  resetarForm() {
    this.novoEmprestimo = {
      usuario: { id: null },
      livro: { id: null },
      dataEmprestimo: '',
      dataDevolucao: '',
      status: 'ATIVO'
    };
    this.recomendacoes = [];
  }
}