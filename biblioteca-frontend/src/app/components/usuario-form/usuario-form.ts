import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { BibliotecaService } from '../../services/biblioteca.service';
@Component({
  selector: 'app-usuario-form',
  standalone: true, 
  imports: [CommonModule, FormsModule], 
  templateUrl: './usuario-form.html',
  styleUrl: './usuario-form.css',
})
export class UsuarioFormComponent {
  usuario = { nome: '', email: '', telefone: '', dataCadastro: '' };

  constructor(private service: BibliotecaService) {}

formatarTelefone() {
  let valor = this.usuario.telefone.replace(/\D/g, ''); 

  if (valor.length > 0) {
    valor = valor.replace(/^(\d{2})(\d)/g, '($1) $2');
    valor = valor.replace(/(\d{5})(\d)/, '$1-$2');
  }

  this.usuario.telefone = valor.substring(0, 15);
}

salvarUsuario() { 
  this.service.salvarUsuario(this.usuario).subscribe({
    next: (res) => {
      alert('✅ Usuário salvo com sucesso!');
      this.usuario = { nome: '', email: '', telefone: '', dataCadastro: '' };
    },
    error: (err) => {
      if (err.status === 400 && err.error) {
        const erros = err.error;
        let mensagemFinal = '⚠️ Verifique os seguintes campos:\n\n';

        if (erros.email) mensagemFinal += `• E-mail: ${erros.email}\n`;
        if (erros.nome) mensagemFinal += `• Nome: ${erros.nome}\n`;
        if (erros.telefone) mensagemFinal += `• Telefone: ${erros.telefone}\n`;
        if (erros.dataCadastro) mensagemFinal += `• Data: ${erros.dataCadastro}\n`;

        alert(mensagemFinal);
      } else {
        alert('❌ Erro inesperado ao tentar salvar no servidor.');
      }
    }
  });
}
}