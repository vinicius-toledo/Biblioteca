import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { BibliotecaService } from '../../services/biblioteca.service';
@Component({
  selector: 'app-usuario-form',
  standalone: true, // Adicione isso se não tiver
  imports: [CommonModule, FormsModule], 
  templateUrl: './usuario-form.html',
  styleUrl: './usuario-form.css',
})
export class UsuarioFormComponent {
  usuario = { nome: '', email: '', telefone: '', dataCadastro: '' };

  constructor(private service: BibliotecaService) {}

  salvarUsuario() { 
    this.service.salvarUsuario(this.usuario).subscribe({
      next: (res) => {
        alert('Usuário salvo com sucesso!');
        this.usuario = { nome: '', email: '', telefone: '', dataCadastro: '' };
      },
      error: (err) => alert('Erro ao salvar no Java!')
    });
  }
}