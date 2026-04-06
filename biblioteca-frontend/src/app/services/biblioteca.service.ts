import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario, Livro, Emprestimo } from '../models/biblioteca.models';

@Injectable({
  providedIn: 'root'
})
export class BibliotecaService {

  private apiUrl = 'http://localhost:8080'; // Endereço do seu Spring Boot

  constructor(private http: HttpClient) { }

  // --- USUÁRIOS ---
  salvarUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(`${this.apiUrl}/usuarios`, usuario);
  }

  listarUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.apiUrl}/usuarios`);
  }

  // --- LIVROS ---
  
  // Busca automática pelo Google
  salvarLivroGoogle(titulo: string): Observable<Livro> {
    return this.http.post<Livro>(`${this.apiUrl}/google-books/adicionar?titulo=${titulo}`, {});
  }

  // Cadastro Manual
  salvarLivroManual(livro: any): Observable<Livro> {
    return this.http.post<Livro>(`${this.apiUrl}/livros`, livro);
  }

  listarLivros(): Observable<Livro[]> {
    return this.http.get<Livro[]>(`${this.apiUrl}/livros`);
  }

  // --- EMPRÉSTIMOS ---
  
  salvarEmprestimo(emprestimo: Emprestimo): Observable<Emprestimo> {
    return this.http.post<Emprestimo>(`${this.apiUrl}/emprestimos`, emprestimo);
  }

  // NOVO: Necessário para carregar a tabela de histórico no Front
  listarEmprestimos(): Observable<Emprestimo[]> {
    return this.http.get<Emprestimo[]>(`${this.apiUrl}/emprestimos`);
  }

  // NOVO: Necessário para o botão "Devolver" (Requisito 2 do desafio)
  atualizarStatusEmprestimo(id: number, novoStatus: string): Observable<Emprestimo> {
    // O corpo do objeto deve bater com o seu PatchEmprestimoDTO no Java
    return this.http.patch<Emprestimo>(`${this.apiUrl}/emprestimos/${id}/status`, { status: novoStatus });
  }

  // --- RECOMENDAÇÕES (Requisito 3) ---
  getRecomendacoes(usuarioId: number): Observable<Livro[]> {
    return this.http.get<Livro[]>(`${this.apiUrl}/usuarios/${usuarioId}/recomendacoes`);
  }
}