import { Routes } from '@angular/router';
import { UsuarioFormComponent } from './components/usuario-form/usuario-form';
import { LivroGoogleComponent } from './components/livro-google/livro-google';
import { EmprestimoGestaoComponent } from './components/emprestimo-gestao/emprestimo-gestao';

export const routes: Routes = [
  { path: 'usuarios', component: UsuarioFormComponent },
  { path: 'livros', component: LivroGoogleComponent }, 
  { path: 'emprestimos', component: EmprestimoGestaoComponent },
  { path: '', redirectTo: '/usuarios', pathMatch: 'full' } 
];