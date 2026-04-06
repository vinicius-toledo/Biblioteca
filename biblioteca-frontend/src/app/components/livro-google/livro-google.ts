import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BibliotecaService } from '../../services/biblioteca.service';

@Component({
  selector: 'app-livro-google',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './livro-google.html',
  styleUrl: './livro-google.css',
})
export class LivroGoogleComponent {
  termoBusca: string = ''; 
  
  novoLivro = {
    titulo: '',
    autor: '',
    isbn: '',
    dataPublicacao: '', 
    categoria: ''
  };

  constructor(private bibliotecaService: BibliotecaService) {}

  buscarNoGoogle() {
    if (!this.termoBusca) { 
      alert('Digite um título para buscar');
      return;
    }
    this.bibliotecaService.salvarLivroGoogle(this.termoBusca).subscribe({
      next: (res) => alert('Livro do Google adicionado com sucesso!'),
      error: (err) => alert('Erro no Google. Tente o cadastro manual.')
    });
  }

  salvarManual() {
    if (!this.novoLivro.titulo || !this.novoLivro.categoria) {
      alert('Título e Categoria são obrigatórios!');
      return;
    }

    this.bibliotecaService.salvarLivroManual(this.novoLivro).subscribe({
      next: (res) => {
        alert('Livro cadastrado com sucesso!');
        this.limparForm();
      },
      error: (err) => alert('Erro ao salvar manualmente.')
    });
  }

  limparForm() {
    this.novoLivro = { titulo: '', autor: '', isbn: '', dataPublicacao: '', categoria: '' };
    this.termoBusca = '';
  }
}