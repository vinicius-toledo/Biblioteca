import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario, Livro, Emprestimo } from '../models/biblioteca.models';

@Injectable({
  providedIn: 'root'
})
export class BibliotecaService {

  private apiUrl = 'http://localhost:8080'; 

  constructor(private http: HttpClient) { }

  salvarUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(`${this.apiUrl}/usuarios`, usuario);
  }

  listarUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.apiUrl}/usuarios`);
  }

  
  salvarLivroGoogle(titulo: string): Observable<Livro> {
    return this.http.post<Livro>(`${this.apiUrl}/google-books/adicionar?titulo=${titulo}`, {});
  }


  salvarLivroManual(livro: any): Observable<Livro> {
    return this.http.post<Livro>(`${this.apiUrl}/livros`, livro);
  }

  listarLivros(): Observable<Livro[]> {
    return this.http.get<Livro[]>(`${this.apiUrl}/livros`);
  }

  
  salvarEmprestimo(emprestimo: Emprestimo): Observable<Emprestimo> {
    return this.http.post<Emprestimo>(`${this.apiUrl}/emprestimos`, emprestimo);
  }


  listarEmprestimos(): Observable<Emprestimo[]> {
    return this.http.get<Emprestimo[]>(`${this.apiUrl}/emprestimos`);
  }

  atualizarStatusEmprestimo(id: number, novoStatus: string): Observable<Emprestimo> {
    return this.http.patch<Emprestimo>(`${this.apiUrl}/emprestimos/${id}/status`, { status: novoStatus });
  }

  getRecomendacoes(usuarioId: number): Observable<Livro[]> {
    return this.http.get<Livro[]>(`${this.apiUrl}/usuarios/${usuarioId}/recomendacoes`);
  }
}