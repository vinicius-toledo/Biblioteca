export interface Usuario {
  id?: number;
  nome: string;
  email: string;
  telefone: string;
  dataCadastro: string;
}

export interface Livro {
  id?: number;
  titulo: string;
  autor: string;
  categoria: string;
  isbn: string;
}

export interface Emprestimo {
  id?: number;
  usuarioId: number;
  livroId: number;
  dataEmprestimo: string;
  dataDevolucao: string;
  status?: string;
}